package uz.gita.mobilebankingcompose.ui.signin.signin

import kotlinx.coroutines.flow.StateFlow

interface SignInViewModel {

    val state :StateFlow<SignInContract.State>

    fun onEvent(event: SignInContract.Event)
}