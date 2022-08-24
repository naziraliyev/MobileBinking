package uz.gita.mobilebankingcompose.domain.picodeUseCase

import kotlinx.coroutines.flow.Flow

interface PinCodeUseCase {
    fun savePinCode(code: String): Flow<Unit>

    fun checkPinCode(code: String): Flow<Boolean>

    fun saveFingerPrintState(state: Boolean): Flow<Boolean>

    fun checkFingerPrintState(): Flow<Boolean>

}