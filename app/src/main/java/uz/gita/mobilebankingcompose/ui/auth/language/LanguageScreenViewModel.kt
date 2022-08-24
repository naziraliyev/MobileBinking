package uz.gita.mobilebankingcompose.ui.auth.language


import kotlinx.coroutines.flow.StateFlow

interface LanguageScreenViewModel {
    val uiState: StateFlow<LanguageContract.State>
    fun eventDispatcher(event: LanguageContract.Event)

}
