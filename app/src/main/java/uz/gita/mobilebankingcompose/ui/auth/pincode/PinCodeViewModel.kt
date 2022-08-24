package uz.gita.mobilebankingcompose.ui.auth.pincode

import kotlinx.coroutines.flow.StateFlow

interface PinCodeViewModel {
    val state: StateFlow<PinCodeContract.State>

    fun onEvent(event: PinCodeContract.Event)
}