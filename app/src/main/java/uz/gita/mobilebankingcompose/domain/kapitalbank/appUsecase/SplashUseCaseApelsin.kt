package uz.gita.mobilebankingcompose.domain.kapitalbank.appUsecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.mobilebankingcompose.data.model.SplashData

interface SplashUseCaseApelsin {
    fun currentLanguage(): Flow<String>
    fun navigateToNextScreen():Flow<SplashData>
}