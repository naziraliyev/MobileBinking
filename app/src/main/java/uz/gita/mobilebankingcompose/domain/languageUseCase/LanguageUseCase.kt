package uz.gita.mobilebankingcompose.domain.languageUseCase

import kotlinx.coroutines.flow.Flow

interface LanguageUseCase {

    fun getAppLanguages(): Flow<List<String>>

    fun setAppLanguage(language: String): Flow<Unit>

    fun currentLanguage(): Flow<String>

}