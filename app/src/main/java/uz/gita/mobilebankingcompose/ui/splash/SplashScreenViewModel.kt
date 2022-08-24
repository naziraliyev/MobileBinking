package uz.gita.mobilebankingcompose.ui.splash

import kotlinx.coroutines.flow.StateFlow

interface SplashScreenViewModel {
    val state: StateFlow<SplashContract.State>
}