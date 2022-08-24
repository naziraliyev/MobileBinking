package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.RepeatPinCodeUseCase
import javax.inject.Inject

@HiltViewModel
class RepeatPinCodeViewModelImpl @Inject constructor(
    private val direction: RepeatPinCodeDirection,
    private val useCase: RepeatPinCodeUseCase
) : ViewModel(), RepeatPinCodeViewModel {

    private val _state = MutableStateFlow(RepeatPinCodeContract.State())
    override val state = _state.asStateFlow()

    override fun onEventPin(eventPin: RepeatPinCodeContract.EventPin) {
        when (eventPin) {
            is RepeatPinCodeContract.EventPin.OnBackPressed->{
                direction.back()
            }
            is RepeatPinCodeContract.EventPin.SendCode -> {
                useCase.checkPinCode(eventPin.code).onEach { code ->
                    Timber.d("code $code")
                    if (code) {
                        direction.navigateToMainScreen()
                    }
                    else {
                        reduce {
                            it.copy(
                                errorStatus = code,
                                message = R.string.wrong_code.toString()
                            )
                        }
                        delay(500)
                        reduce {
                            it.copy(
                                errorStatus = !code,
                                message = R.string.enter_pin_code.toString()
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is RepeatPinCodeContract.EventPin.FilledCode -> {
                reduce { it.copy(statusPin = true) }
            }
            is RepeatPinCodeContract.EventPin.UnFilledCode -> {
                reduce { it.copy(statusPin = false) }
            }
        }

    }


    private fun reduce(content: (old: RepeatPinCodeContract.State) -> RepeatPinCodeContract.State) {
        val old = _state.value
        val new = content(old)
        _state.value = new
    }
}