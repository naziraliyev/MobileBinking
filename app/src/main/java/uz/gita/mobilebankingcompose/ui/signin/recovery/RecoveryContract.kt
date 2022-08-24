package uz.gita.mobilebankingcompose.ui.signin.recovery

class RecoveryContract {
    data class State(
        val errorPhone:Boolean = false,
        val isFilledPhoneNumber: Boolean = false,
    )

    sealed class Event{

        data class Recovery(val phoneNumber:String, val stateWindow:String):Event()
    }
}