package uz.gita.mobilebankingcompose.ui.signin.signin

class SignInContract {

    data class State(
        val isFilledPhoneNumber: Boolean = false,
        val isFilledPassword: Boolean = false,
        val errorPhone:Boolean = false,
        val errorPassword:Boolean = false
    )

    sealed class Event {
        object ForgetPassword:Event()
        object RegistrationButton:Event()
        data class Registration(
            val phoneNumber: String,
            val password: String,
        ) : SignInContract.Event()
    }
}