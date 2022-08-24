package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.laguage

import uz.gita.mobilebankingcompose.ui.auth.language.LanguageContract

class LanguageScreenContract {
    data class State(
        val languages: List<String> = emptyList(),
        val currentLanguage: String
    )

    sealed class DispatcherEvent(){
        data class AcceptedLanguage( val language:String):DispatcherEvent()
    }

}