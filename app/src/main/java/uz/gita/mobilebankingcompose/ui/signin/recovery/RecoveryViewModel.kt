package uz.gita.mobilebankingcompose.ui.signin.recovery

import kotlinx.coroutines.flow.StateFlow

interface RecoveryViewModel {
    val state:StateFlow<RecoveryContract.State>

    fun onEvent(event: RecoveryContract.Event)
}