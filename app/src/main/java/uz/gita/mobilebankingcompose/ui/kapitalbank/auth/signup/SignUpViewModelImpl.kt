package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.signup

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.data.model.onResource
import uz.gita.mobilebankingcompose.data.model.onSuccess
import uz.gita.mobilebankingcompose.data.model.onText
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.SignUpUseCaseApelsin
import uz.gita.mobilebankingcompose.domain.signUpUseCase.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModelImpl @Inject constructor(
    private val signUpDirection: SignUpDirection,
    private val useCase: SignUpUseCaseApelsin
) : ViewModel(), SignUpViewModel {

    private val _state = MutableStateFlow(SignUpContract.State())
    private val sideEffect:((effect:SignUpContract.Effect)->Unit)? = null
    override val state= _state.asStateFlow()

    override fun dispatchersEvent(event: SignUpContract.Event) {
        when(event){
           is SignUpContract.Event.RegistrationApelsin ->{
               reduce { it.copy(
                   isProgress = true,
                   isRegisterAvailable = false
               ) }
               val data = SignUpData(
                   firstname = "No NAME",
                   lastname = "NO NAME",
                   phoneNumber = event.phoneNumber,
                   password = "123456789"
               )

               useCase.signUpData(data).onEach { resultData ->
                resultData.onSuccess {
                    signUpDirection.navigateToNextScreen(data)
                    setDefaultState()
                }
                   resultData.onText {
                       sideEffect?.invoke(SignUpContract.Effect.OnText(message))
                       setDefaultState()
                   }
                   resultData.onResource {
                       sideEffect?.invoke(SignUpContract.Effect.OnResource(resourceId))
                       setDefaultState()
                   }

               }.launchIn(viewModelScope)
           }
        }

    }

    private fun setDefaultState() {
        _state.value = SignUpContract.State()
    }

    override fun sideEffect(sideEffect: (sideEffect: SignUpContract.Effect) -> Unit) {

    }

    fun reduce(content:(old:SignUpContract.State)->SignUpContract.State){
        val oldData = _state.value
        val newData = content(oldData)
        _state.value = newData
    }
}