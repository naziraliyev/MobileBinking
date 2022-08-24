package uz.gita.mobilebankingcompose.ui.signin.recovery

import uz.gita.mobilebankingcompose.data.model.auth.SignUpData

interface RecoveryDirection {

    fun navigateToSignUp(data: SignUpData)
    fun popBackStack()
}