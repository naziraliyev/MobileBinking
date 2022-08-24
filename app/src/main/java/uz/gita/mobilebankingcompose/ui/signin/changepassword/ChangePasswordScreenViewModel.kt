package uz.gita.mobilebankingcompose.ui.signin.changepassword

import kotlinx.coroutines.flow.StateFlow

interface ChangePasswordScreenViewModel {
    val state:StateFlow<ChangePasswordContract.State>

    fun onEvent(event: ChangePasswordContract.Event)
}