package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.privacyScreen

class PrivacyContract {
    data class State(
        val acceptedButton:Boolean = false
    )

    enum class Event{
        CHECK,
        ACCEPT
    }
}