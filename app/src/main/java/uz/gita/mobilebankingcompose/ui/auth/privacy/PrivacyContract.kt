package uz.gita.mobilebankingcompose.ui.auth.privacy

class PrivacyContract {

    data class State(var buttonAcceptStatus: Boolean = false)

    enum class Event{
        CHECK,
        ACCEPT
    }
}