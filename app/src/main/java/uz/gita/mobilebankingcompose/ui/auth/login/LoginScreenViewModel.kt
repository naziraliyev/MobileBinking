package uz.gita.mobilebankingcompose.ui.auth.login

import kotlinx.coroutines.flow.StateFlow

interface LoginScreenViewModel {
    val state: StateFlow<LoginContract.State>

    fun onEvent(event: LoginContract.Event)
    fun sideEffect(sideEffect: (sideEffect:LoginContract.SideEffect)->Unit)
}