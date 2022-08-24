package uz.gita.mobilebankingcompose.ui.onboarding.onboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobilebankingcompose.ui.signin.changepassword.ChangePasswordContract

class OnBoardViewModel :ViewModel(){

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> get() = _currentPage

    fun setCurrentPage(currentPage: Int) {
        _currentPage.value = currentPage
    }
}