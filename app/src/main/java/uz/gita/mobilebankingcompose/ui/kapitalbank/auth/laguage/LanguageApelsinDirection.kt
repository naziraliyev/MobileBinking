package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.laguage

import kotlinx.coroutines.flow.StateFlow

interface LanguageApelsinDirection {

    fun navigateToSignUpScreen():StateFlow<Unit>
    fun navigateToPrivacyScreen():StateFlow<Unit>
}