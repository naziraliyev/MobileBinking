package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode


class FirstPinCodeContract {
    sealed class EventPin{
        data class SendCode(val code:String):EventPin()
        object FilledCode:EventPin()
        object UnFilledCode:EventPin()
        object OnBackPressed:EventPin()
    }
    data class State(
        val statusPin:Boolean = false
    )
}