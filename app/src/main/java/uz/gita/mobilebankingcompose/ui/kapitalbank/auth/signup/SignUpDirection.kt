package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.signup

import uz.gita.mobilebankingcompose.data.model.auth.SignUpData

interface SignUpDirection {
    fun navigateToNextScreen(data: SignUpData)
}