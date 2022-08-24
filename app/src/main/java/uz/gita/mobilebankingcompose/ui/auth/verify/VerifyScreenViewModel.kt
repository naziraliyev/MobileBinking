package uz.gita.mobilebankingcompose.ui.auth.verify

import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData

interface VerifyScreenViewModel {

    val state:StateFlow<VerifyContract.State>

    fun onEvent(event: VerifyContract.Event)
    fun initData(data: SignUpData)
}