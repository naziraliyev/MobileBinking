package uz.gita.mobilebankingcompose.ui.auth.privacy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrivacyViewModelImpl @Inject constructor(private val privacyDirection: PrivacyDirection) :
    ViewModel(), PrivacyViewModel {

    private val _state = MutableStateFlow(PrivacyContract.State())
    override val state = _state.asStateFlow()
    private var job: Job? = null
    private var clicked = false
    override fun onEvent(event: PrivacyContract.Event) {
        when (event) {

            PrivacyContract.Event.ACCEPT -> {
                viewModelScope.launch {
                    if (!clicked) {
                        clicked = true
                        delay(300)
                        privacyDirection.navigateToSignInScreen()
                        job.let {
                            delay(300)
                            clicked = false
                        }
                        job?.cancel()
                    }
                }
            }
            PrivacyContract.Event.CHECK -> {
                reduce { it.copy(buttonAcceptStatus = !it.buttonAcceptStatus) }
            }
        }
    }

    private fun reduce(content: (old: PrivacyContract.State) -> PrivacyContract.State) {
        val old = _state.value
        val newSate = content(old)
        _state.value = newSate
    }
}