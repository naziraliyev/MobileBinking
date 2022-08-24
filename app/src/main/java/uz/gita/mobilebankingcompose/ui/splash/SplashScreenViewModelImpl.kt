package uz.gita.mobilebankingcompose.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mobilebankingcompose.data.model.SplashData
import uz.gita.mobilebankingcompose.domain.splashUscase.SplashUseCase
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(
    private val useCase: SplashUseCase,
    private val direction: SplashScreenDirection

) : ViewModel(),
    SplashScreenViewModel {
    private val _state = MutableStateFlow(SplashContract.State())
    override val state = _state.asStateFlow()

    init {
        setLanguage()
        navigate()
    }

    private fun navigate() {
        viewModelScope.launch {
            delay(2000)
            useCase.navigateNextScreen().collectLatest { data ->
                when (data) {
                    SplashData.LANGUAGE_SCREEN -> direction.navigateToLanguageScreen()
                    SplashData.PIN_CODE_SCREEN -> direction.navigateToSplashPinCodeScreen()
                    else -> direction.navigateToSignInScreen()
                }

            }
        }
    }

    private fun setLanguage() {
        viewModelScope.launch {
            useCase.currentLanguage()
                .collectLatest { language -> reduce { it.copy(language = language) } }
        }
    }

    private fun reduce(content: (old: SplashContract.State) -> SplashContract.State) {
        val oldState = _state.value
        val newState = content(oldState)
        _state.value = newState
    }
}