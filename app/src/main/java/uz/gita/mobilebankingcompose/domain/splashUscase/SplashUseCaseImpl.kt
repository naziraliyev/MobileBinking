package uz.gita.mobilebankingcompose.domain.splashUscase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebankingcompose.data.model.SplashData
import uz.gita.mobilebankingcompose.data.repository.AppRepository
import javax.inject.Inject

class SplashUseCaseImpl @Inject constructor(private val appRepository: AppRepository) :
    SplashUseCase {
    override fun currentLanguage(): Flow<String> = flow {
        emit(appRepository.currentLanguage())
    }.flowOn(Dispatchers.IO)

    override fun navigateNextScreen(): Flow<SplashData> = flow {
        when (appRepository.isFirstLaunch()) {
            false -> {
                if (appRepository.getPinCode().isEmpty()) emit(SplashData.SIGN_IN_SCREEN)
                else emit(SplashData.PIN_CODE_SCREEN)
            }
            else -> {
                emit(SplashData.LANGUAGE_SCREEN)
            }
        }
    }.flowOn(Dispatchers.IO)
}