package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import uz.gita.mobilebankingcompose.data.model.SplashData
import uz.gita.mobilebankingcompose.domain.kapitalbank.appUsecase.SplashUseCaseApelsin
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val direction: SplashDiractionApelsin,
    private val useCaseApelsin: SplashUseCaseApelsin
) :
    ViewModel(), SplashViewModel {

    private val _state = MutableStateFlow(SplashContractApelsin.State())
    override val state = _state.asStateFlow()

    init {
        navigate()
        setLanguage()
    }

    private fun navigate() {
        viewModelScope.launch {
            delay(100)
            useCaseApelsin.navigateToNextScreen().collectLatest { data ->
                Timber.d("dataAAAAAAA $data")

                when (data) {
                    SplashData.PIN_CODE -> direction.splashToPinCode("main")

                    else -> direction.splashToAuthApelsin()
                }
            }
        }

    }

    private fun setLanguage() {
        viewModelScope.launch {
            useCaseApelsin.currentLanguage()
                .collectLatest { language -> reduce { it.copy(language = language) } }
        }
    }

    private fun reduce(content: (old: SplashContractApelsin.State) -> SplashContractApelsin.State) {
        val oldState = _state.value
        val newState = content(oldState)
        _state.value = newState
    }
}