package uz.gita.mobilebankingcompose.ui.auth.language

class LanguageContract {

    sealed class Event {
        data class AcceptedLanguage(val language:String): Event()
    }

    data class State(
        val language:List<String> = emptyList(),
        val currentLanguage: String
    )
    sealed class SideEffect {
        data class SetLanguage(val language: String) : SideEffect()
    }
}