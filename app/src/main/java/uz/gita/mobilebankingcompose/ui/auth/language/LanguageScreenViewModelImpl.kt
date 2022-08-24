package uz.gita.mobilebankingcompose.ui.auth.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.mobilebankingcompose.domain.languageUseCase.LanguageUseCase
import javax.inject.Inject

@HiltViewModel
class LanguageScreenViewModelImpl @Inject constructor(
    private val useCase: LanguageUseCase,
    private val languageScreenDirection: LanguageScreenDirection
) : ViewModel(), LanguageScreenViewModel {
    private var job: Job? = null
    private var clicked = false
    private val _state = MutableStateFlow(LanguageContract.State(currentLanguage =""))

    override val uiState = _state.asStateFlow()

    init {
        languagesList()
        currentLanguage()
    }

    override fun eventDispatcher(event: LanguageContract.Event) {
        when (event) {
            is LanguageContract.Event.AcceptedLanguage -> {
                viewModelScope.launch {
                    useCase.setAppLanguage(event.language)
                        .collectLatest { language1 ->
                            reduce { it.copy(currentLanguage = language1.toString()) }
                        }
                    if (!clicked) {
                        clicked = true
                        languageScreenDirection.navigateToPrivacyScreen()
                        job.let {
                            delay(500)
                            clicked = false
                        }
                        job?.cancel()
                    }

                }
            }
        }
    }
    private fun languagesList() {
        viewModelScope.launch {
            useCase
                .getAppLanguages()
                .collectLatest { list -> reduce { it.copy(language = list) } }
        }
    }

    private fun currentLanguage() {
        viewModelScope.launch {
            useCase
                .currentLanguage()
                .collectLatest { language -> reduce { it.copy(currentLanguage = language) } }
        }
    }

private fun reduce(content: (old: LanguageContract.State) -> LanguageContract.State) {
    val oldState = _state.value
    val newState = content(oldState)
    _state.value = newState
}

}

