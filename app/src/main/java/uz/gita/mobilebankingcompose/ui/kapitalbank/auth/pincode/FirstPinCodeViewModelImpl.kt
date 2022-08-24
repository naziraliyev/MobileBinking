package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.FirstPinCodeUseCase
import uz.gita.mobilebankingcompose.domain.signUpUseCase.SignUpUseCaseImpl
import javax.inject.Inject

@HiltViewModel
class FirstPinCodeViewModelImpl @Inject constructor(
    private val direction: FirstPinCodeDirection,
    private val useCase: FirstPinCodeUseCase
    ) : ViewModel(), FirstPinCodeViewModel {

    private val _state = MutableStateFlow(FirstPinCodeContract.State())
    override val state = _state.asStateFlow()

    override fun onEventPin(eventPin: FirstPinCodeContract.EventPin) {
        when (eventPin) {
            is FirstPinCodeContract.EventPin.OnBackPressed->{
                direction.backPrivacy()
            }
            is FirstPinCodeContract.EventPin.SendCode -> {
                viewModelScope.launch {
                    useCase.savePinCode(code = eventPin.code).collectLatest {
                        direction.navigateToNextPin("pincode")
                    }
                }
            }
            is FirstPinCodeContract.EventPin.FilledCode -> {reduce { it.copy(statusPin = true) }}
            is FirstPinCodeContract.EventPin.UnFilledCode -> {reduce { it.copy(statusPin = false) }}
        }
    }

    private fun reduce(content: (old: FirstPinCodeContract.State) -> FirstPinCodeContract.State) {
        val old = _state.value
        val new = content(old)
        _state.value = new
    }
}