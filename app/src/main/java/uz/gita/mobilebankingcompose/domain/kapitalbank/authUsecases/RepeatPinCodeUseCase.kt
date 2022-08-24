package uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases

import kotlinx.coroutines.flow.Flow

interface RepeatPinCodeUseCase {
    fun savePinCode(code: String): Flow<Unit>
    fun checkPinCode(code: String): Flow<Boolean>
}