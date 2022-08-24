package uz.gita.mobilebankingcompose.domain.kapitalbank.appUsecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.gita.mobilebankingcompose.data.model.SplashData
import uz.gita.mobilebankingcompose.data.repository.AppRepository
import uz.gita.mobilebankingcompose.domain.kapitalbank.appUsecase.SplashUseCaseApelsin
import javax.inject.Inject

class SplashUseCaseApelsinImpl @Inject constructor(private val appRepository: AppRepository) :
    SplashUseCaseApelsin {
    override fun currentLanguage(): Flow<String> = flow {

        Timber.d("dataAAAAAAALL ${appRepository.getIsLoggedIn()}")
        emit(appRepository.currentLanguage())
    }.flowOn(Dispatchers.IO)

    override fun navigateToNextScreen(): Flow<SplashData> = flow {
        Timber.d("dataAAAAAAALL ${appRepository.getIsLoggedIn()}")

        when(appRepository.getIsLoggedIn()){
            false->{
                if (appRepository.getPinCodeApelsin().isEmpty()) emit(SplashData.SIGNUP_A)
                else emit(SplashData.PIN_CODE)
            }
            else-> emit(SplashData.PIN_CODE)
        }
    }.flowOn(Dispatchers.IO)
}