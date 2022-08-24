package uz.gita.mobilebankingcompose.ui.signin.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModelImpl @Inject constructor(private val direction: SigInDirection) : ViewModel(),
    SignInViewModel {
    private val _state = MutableStateFlow(SignInContract.State())
    override val state = _state.asStateFlow()
    private var job: Job? = null
    private var clicked = false
    override fun onEvent(event: SignInContract.Event) {
        when (event) {
            is SignInContract.Event.Registration -> {


                viewModelScope.launch {
//                    reduce {
//                        it.copy(errorPassword = true)
//                    }
                    if (!clicked) {
                        clicked = true
                        delay(500)
                        direction.signInToPiCodeScreen()
                        job.let {
                            delay(500)
                            clicked = false
                        }
                        job?.cancel()
                    }
                }
            }
            is SignInContract.Event.ForgetPassword -> {
                reduce { it.copy() }
                viewModelScope.launch {
                    if (!clicked) {
                        clicked = true
                        delay(500)
                        direction.signInToRecovery()
                        job.let {
                            delay(500)
                            clicked = false
                        }
                        job?.cancel()
                    }
                }
            }
            is SignInContract.Event.RegistrationButton -> {
                reduce { it.copy() }
                viewModelScope.launch {
                    if (!clicked) {
                        clicked = true
                        delay(500)
                        direction.signInToSignUp()
                        job.let {
                            delay(500)
                            clicked = false
                        }
                        job?.cancel()
                    }
                }
            }

        }
    }

    private fun reduce(content: (old: SignInContract.State) -> SignInContract.State) {
        val old = _state.value
        val newContent = content(old)
        _state.value = newContent
    }
}