package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.signup

import uz.gita.mobilebankingcompose.ui.auth.login.LoginContract

class SignUpContract {

    data class State(
        val isRegisterAvailable:Boolean=false,
        val isProgress:Boolean = false
    )

    sealed class Event{
        data class RegistrationApelsin(
            val firstName: String,
            val lastName: String,
            val phoneNumber: String,
            val password: String,
        ):Event()
    }

    sealed class Effect{
        data class OnText(val message: String) : SignUpContract.Effect()
        data class OnResource(val resId: Int) : SignUpContract.Effect()
    }
}