package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.privacyScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrivacyViewModelImpl @Inject constructor(private val directionApelsin: PrivacyDirectionApelsin) :
    ViewModel(), PrivacyViewModel {
    private val _state = MutableStateFlow(PrivacyContract.State())
    override val state = _state.asStateFlow()

    private val job: Job? = null
    private var clicked = false

    override fun onEvent(event: PrivacyContract.Event) {
        when (event) {
            PrivacyContract.Event.CHECK -> {
                reduce { it.copy(acceptedButton = !it.acceptedButton) }
            }
            PrivacyContract.Event.ACCEPT -> {
                viewModelScope.launch {
                    if (!clicked) {
                        clicked = true
                        delay(300)
                        directionApelsin.navigateToFirstPin()
                        job.let {
                            delay(500)
                            clicked = true
                        }
                        job?.cancel()
                    }
                }
            }
        }
    }

    private fun reduce(content: (old: PrivacyContract.State) -> PrivacyContract.State) {
        val old = _state.value
        val new = content(old)
        _state.value = new
    }
}