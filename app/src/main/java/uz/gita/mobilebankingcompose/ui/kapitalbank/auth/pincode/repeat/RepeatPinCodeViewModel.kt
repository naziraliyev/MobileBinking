package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat

import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.FirstPinCodeContract

interface RepeatPinCodeViewModel {

    val state: StateFlow<RepeatPinCodeContract.State>

    fun onEventPin(eventPin: RepeatPinCodeContract.EventPin)
}