package uz.gita.mobilebankingcompose.ui.signin.changepassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordScreenViewModelImpl @Inject constructor(private val direction: ChangePasswordDirection):ViewModel(),ChangePasswordScreenViewModel {

   private val _state = MutableStateFlow(ChangePasswordContract.State())
    override val state = _state.asStateFlow()

    override fun onEvent(event: ChangePasswordContract.Event) {
        when(event){
            is ChangePasswordContract.Event.ChangePassword->{
                reduce { it.copy(isEquals = true) }
                viewModelScope.launch {
                    delay(1000)
                }
            }
        }
    }

    private fun reduce(content:(old:ChangePasswordContract.State)->ChangePasswordContract.State){
        val old = _state.value
        val newContent = content(old)
        _state.value = newContent
    }
}