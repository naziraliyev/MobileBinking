package uz.gita.mobilebankingcompose.ui.auth.privacy

import kotlinx.coroutines.flow.StateFlow

interface PrivacyViewModel {
    val state : StateFlow<PrivacyContract.State>

    fun onEvent(event:PrivacyContract.Event)
}