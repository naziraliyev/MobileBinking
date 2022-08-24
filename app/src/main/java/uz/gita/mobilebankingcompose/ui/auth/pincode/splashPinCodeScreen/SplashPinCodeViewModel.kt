package uz.gita.mobilebankingcompose.ui.auth.pincode.splashPinCodeScreen

import kotlinx.coroutines.flow.StateFlow

interface SplashPinCodeViewModel {

    val state :StateFlow<SplashPinCodeContract.State>

    fun onEvent(event: SplashPinCodeContract.Event)

    fun sideEffect(effect: (SplashPinCodeContract.SideEffect)->Unit)
}