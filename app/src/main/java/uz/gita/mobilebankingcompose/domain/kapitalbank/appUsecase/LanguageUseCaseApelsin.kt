package uz.gita.mobilebankingcompose.domain.kapitalbank.appUsecase

import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.Language

interface LanguageUseCaseApelsin {

    fun setLanguage(language: String): Flow<Unit>
    fun getLanguage(): Flow<Unit>
    fun currentLanguage(): Flow<Unit>
}