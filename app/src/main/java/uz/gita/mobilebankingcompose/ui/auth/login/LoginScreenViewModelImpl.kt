package uz.gita.mobilebankingcompose.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.data.model.onResource
import uz.gita.mobilebankingcompose.data.model.onSuccess
import uz.gita.mobilebankingcompose.data.model.onText
import uz.gita.mobilebankingcompose.domain.signUpUseCase.SignUpUseCase
import uz.gita.mobilebankingcompose.utils.toPhoneNumber
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModelImpl
@Inject constructor(
    private val loginScreenDirection: LoginScreenDirection,
    private val useCase: SignUpUseCase
) : ViewModel(), LoginScreenViewModel {
    private val _state = MutableStateFlow(LoginContract.State())
    private var sideEffect1: ((sideEffect: LoginContract.SideEffect) -> Unit)? = null

    fun reduce(content: (old: LoginContract.State) -> LoginContract.State) {
        val old = _state.value
        val newState = content(old)
        _state.value = newState
    }

    override val state = _state.asStateFlow()

    override fun onEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.Registration -> {
                reduce {
                    it.copy(
                        isProgress = true,
                        isActiveRegisterButton = false,
                        isBackButtonEnabled = false,
                        isSignInButtonEnabled = false
                    )
                }
                val data = SignUpData(
                    firstname = event.firstName,
                    lastname = event.lastName,
                    phoneNumber = toPhoneNumber(event.phoneNumber),
                    password = event.password
                )

                useCase.signUp(data)
                    .onEach { resultData ->
                        resultData.onSuccess {
                            loginScreenDirection.navigateToCheckActivityScreen(data)
                            setDefaultState()
                        }
                        resultData.onText {
                            Timber.d("sign up onText: $message")
                            sideEffect1?.invoke(LoginContract.SideEffect.OnText(message))
                            setDefaultState()
                        }
                        resultData.onResource {
                            Timber.d("sign up onResource: $resourceId")
                            sideEffect1?.invoke(
                                LoginContract.SideEffect.OnResource(
                                    resourceId
                                )
                            )
                            setDefaultState()
                        }
                    }.launchIn(viewModelScope)
            }
            is LoginContract.Event.OnBackPressed -> {
                setDefaultState()
                loginScreenDirection.back()
            }
        }
    }

    private fun setDefaultState() {
        _state.value = LoginContract.State()
    }

    override fun sideEffect(sideEffect: (sideEffect: LoginContract.SideEffect) -> Unit) {
        sideEffect1 = sideEffect
    }

}