package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.verifyScreen

import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData

interface VerifyViewModel {

    val state :StateFlow<VerifyContract.State>

    fun onEvent(event: VerifyContract.Event)
    fun initData(data: SignUpData)
}