package uz.gita.mobilebankingcompose.ui.signin.recovery

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
class RecoveryViewModelImpl @Inject constructor(private val direction: RecoveryDirection):ViewModel(),RecoveryViewModel {

    private val _state = MutableStateFlow(RecoveryContract.State())
    override val state = _state.asStateFlow()
    private var job: Job? = null
    private var clicked = false
    override fun onEvent(event: RecoveryContract.Event) {
        when(event){
            is RecoveryContract.Event.Recovery ->{
                reduce { it.copy() }
                viewModelScope.launch {
                    if (!clicked) {
                        clicked = true
//                        direction.navigateToSignUp()
                        job.let {
                            delay(300)
                            clicked = false
                        }
                        job?.cancel()
                    }
                }
            }
        }
    }

    private fun reduce(content:(old:RecoveryContract.State)->RecoveryContract.State){
        val old = _state.value
        val newContent = content(old)
        _state.value = newContent
    }
}