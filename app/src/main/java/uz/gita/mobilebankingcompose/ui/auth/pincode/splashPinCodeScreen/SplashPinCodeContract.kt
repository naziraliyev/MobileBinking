package uz.gita.mobilebankingcompose.ui.auth.pincode.splashPinCodeScreen

import uz.gita.mobilebankingcompose.R

class SplashPinCodeContract {
    data class State(
        val isError:Boolean = false,
        val isFingerPrint:Boolean = false,
        val textPinCode: Int = R.string.enter_pin_code
    )
  sealed class Event(){
    data class EnterCode(val  code:String) :Event()
    object FingerPrint:Event()
  }
    enum class SideEffect {
        FINGER_PRINT
    }
}