package uz.gita.mobilebankingcompose.ui.auth.pincode.splashPinCodeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.domain.picodeUseCase.PinCodeUseCase
import javax.inject.Inject

@HiltViewModel
class SplashPinCodeViewModelImpl @Inject constructor(
    private val useCase: PinCodeUseCase,
    private val direction: SplashPinCodeDirection
) : ViewModel(), SplashPinCodeViewModel {
    private val _state = MutableStateFlow(SplashPinCodeContract.State())
    override val state = _state.asStateFlow()

    private var sideEffect: ((SplashPinCodeContract.SideEffect) -> Unit)? = null

    init {
        viewModelScope.launch {
            useCase.checkFingerPrintState().collectLatest { stateFinger ->
                if (stateFinger) sideEffect?.invoke(SplashPinCodeContract.SideEffect.FINGER_PRINT)
            }
        }
    }

    override fun onEvent(event: SplashPinCodeContract.Event) {
        when (event) {
            is SplashPinCodeContract.Event.EnterCode -> {
                useCase.checkPinCode(event.code).onEach { code->
                if (code) direction.navigateToMainScreen()
                    else {
                    reduce {
                        it.copy(
                            textPinCode = R.string.error_pi_code,
                            isError = code
                        )
                    }
                    delay(500)
                    reduce {
                        it.copy(
                            textPinCode = R.string.enter_pin_code,
                            isError = !code
                        )
                    }
                }
                }.launchIn(viewModelScope)
            }
            is SplashPinCodeContract.Event.FingerPrint -> sideEffect?.invoke(SplashPinCodeContract.SideEffect.FINGER_PRINT)
        }
    }

    override fun sideEffect(effect: (SplashPinCodeContract.SideEffect) -> Unit) {
        sideEffect = effect
    }

    private fun reduce(content: (old: SplashPinCodeContract.State) -> SplashPinCodeContract.State) {
        val oldState = _state
        val newState = content(oldState.value)
        _state.value = newState
    }
}