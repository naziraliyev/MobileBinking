package uz.gita.mobilebankingcompose.ui.auth.login

class LoginContract {

    data class State(
        val isProgress: Boolean = false,
        val isActiveRegisterButton: Boolean = true,
        val isBackButtonEnabled: Boolean = true,
        val isSignInButtonEnabled: Boolean = true

    )

    sealed class Event {
        object OnBackPressed : Event()
        data class Registration(
            val firstName: String,
            val lastName: String,
            val phoneNumber: String,
            val password: String,
        ) : Event()

    }

    sealed class SideEffect {
        data class OnText(val message: String) : SideEffect()
        data class OnResource(val resId: Int) : SideEffect()
    }
}