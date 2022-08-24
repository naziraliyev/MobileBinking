package uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases

import kotlinx.coroutines.flow.Flow

interface FirstPinCodeUseCase {
    fun savePinCode(code: String): Flow<Unit>
}