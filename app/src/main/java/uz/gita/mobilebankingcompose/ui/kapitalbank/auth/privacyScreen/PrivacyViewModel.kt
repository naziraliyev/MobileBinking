package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.privacyScreen

import kotlinx.coroutines.flow.StateFlow

interface PrivacyViewModel {

    val state:StateFlow<PrivacyContract.State>

    fun onEvent(event: PrivacyContract.Event)
}