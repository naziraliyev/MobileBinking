package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.verifyScreen


class VerifyContract {

    data class State(
        val isKeyboardEnable:Boolean =true,
        val progressStatus:Boolean = false,
        val timeStatus:Boolean = true,
        val errorStatus:Boolean = false,
        val buttonStatus:Boolean = false,
        val phoneNumber:String = "",
        val timer:Long = 60000L,
        val message:String = "",
        val resourceId :Int = 0
    )

    sealed class Event{
        object OnBackPressed:Event()
        object Retry:Event()
        object ActionKeyBoard:Event()
        object FilledApelsin:Event()
        object UnFilled:Event()
        data class Verified( val code:String):Event()

    }



}