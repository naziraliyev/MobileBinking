package uz.gita.mobilebankingcompose.domain.languageUseCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebankingcompose.data.repository.AppRepository
import javax.inject.Inject

class LanguageUseCaseImpl @Inject constructor(private val appRepository: AppRepository):
    LanguageUseCase {

    override fun getAppLanguages(): Flow<List<String>>  = flow {
        emit(appRepository.languagesList())
    }.flowOn(Dispatchers.IO)

    override fun setAppLanguage(language: String): Flow<Unit>  = flow<Unit> {
        emit(appRepository.setLanguage(language))
    }.flowOn(Dispatchers.IO)

    override fun currentLanguage(): Flow<String> = flow<String> {
        emit(appRepository.currentLanguage())
    }.flowOn(Dispatchers.IO)

}