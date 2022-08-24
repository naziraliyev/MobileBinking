package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.splashscreen

import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobilebankingcompose.ui.splash.SplashContract

interface SplashViewModel {

    val state: StateFlow<SplashContractApelsin.State>
}