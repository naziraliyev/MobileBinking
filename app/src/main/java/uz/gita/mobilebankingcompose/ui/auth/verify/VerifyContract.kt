package uz.gita.mobilebankingcompose.ui.auth.verify

class VerifyContract {

    data class State(
        val isKeyboardEnabled: Boolean = true,
        var progressStatus: Boolean = false,
        val timerStatus: Boolean = true,
        val errorStatus: Boolean = false,
        val acceptButtonStatus: Boolean = false,
        val phoneNumber: String = "",
        val timer: Long = 60000L,
        val message: String = "",
        val resourceId: Int = 0
    )

    sealed class Event{
        object OnBackPressed: Event()
        object Retry: Event()
        object Filled: Event()
        object UnFilled: Event()
        data class OnRegister(val code: String) : Event()
        object ActionKeyboard : Event()
    }


}