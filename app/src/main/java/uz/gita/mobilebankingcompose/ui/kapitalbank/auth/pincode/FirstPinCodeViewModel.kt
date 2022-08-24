package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode

import kotlinx.coroutines.flow.StateFlow

interface FirstPinCodeViewModel {

    val state:StateFlow<FirstPinCodeContract.State>

    fun onEventPin(eventPin: FirstPinCodeContract.EventPin)
}