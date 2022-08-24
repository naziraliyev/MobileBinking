package uz.gita.mobilebankingcompose.ui.auth.pincode

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.domain.picodeUseCase.PinCodeUseCase
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModelImpl @Inject constructor(
    private val useCase: PinCodeUseCase,
    private val pinCodeDirection: PinCodeDirection
) : ViewModel(), PinCodeViewModel {
    private var _state = MutableStateFlow(PinCodeContract.State())
    override val state: StateFlow<PinCodeContract.State> = _state.asStateFlow()
    override fun onEvent(event: PinCodeContract.Event) {
        when (event) {
            is PinCodeContract.Event.Checked -> {
                viewModelScope.launch {
                    useCase.savePinCode(code = event.code)
                        .collectLatest {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) pinCodeDirection.navigateToFingerPrintScreen()
                            else pinCodeDirection.navigateToMainScreen()
                        }
                }

            }
            is PinCodeContract.Event.Skip -> pinCodeDirection.navigateToMainScreen()
            is PinCodeContract.Event.Filled -> reduce { it.copy(textSkip = R.string.text_ready) }
            else -> reduce { it.copy(textSkip = R.string.text_skip) }
        }
    }
    private fun reduce(content: (old: PinCodeContract.State) -> PinCodeContract.State) {
        val oldValue = _state
        val newValue = content(oldValue.value)
        _state.value = newValue
    }
}