package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat

class RepeatPinCodeContract {
    sealed class EventPin{
        data class SendCode(val code:String):EventPin()
        object FilledCode:EventPin()
        object UnFilledCode:EventPin()
        object OnBackPressed:EventPin()
    }
    data class State(
        val statusPin:Boolean = false,
        val errorStatus:Boolean = false,
        val message:String = ""
    )
}