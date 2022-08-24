package uz.gita.mobilebankingcompose.ui.auth.login

import uz.gita.mobilebankingcompose.data.model.auth.SignUpData

interface LoginScreenDirection{
    fun navigateToCheckActivityScreen(data:SignUpData)
    fun back()
}