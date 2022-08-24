package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.signup

import kotlinx.coroutines.flow.StateFlow

interface SignUpViewModel {
    val state:StateFlow<SignUpContract.State>

    fun dispatchersEvent(event: SignUpContract.Event)
    fun sideEffect(sideEffect:(sideEffect:SignUpContract.Effect)->Unit)
}