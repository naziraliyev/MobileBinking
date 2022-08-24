package uz.gita.mobilebankingcompose.domain.splashUscase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebankingcompose.data.model.SplashData

interface SplashUseCase {
    fun currentLanguage(): Flow<String>
    fun navigateNextScreen(): Flow<SplashData>
}