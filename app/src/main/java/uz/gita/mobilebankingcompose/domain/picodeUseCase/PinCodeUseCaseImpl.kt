package uz.gita.mobilebankingcompose.domain.picodeUseCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebankingcompose.data.repository.AppRepository
import uz.gita.mobilebankingcompose.data.repository.auth.AuthRepository
import javax.inject.Inject
import kotlin.math.floor

class PinCodeUseCaseImpl @Inject constructor(
    private val appRepository: AppRepository
    ) : PinCodeUseCase {
    override fun savePinCode(code: String): Flow<Unit> = flow {
        emit(appRepository.savePinCode(code))
    }.flowOn(Dispatchers.IO)

    override fun checkPinCode(code: String): Flow<Boolean> = flow {
        emit(appRepository.getPinCode()==code)
    }.flowOn(Dispatchers.IO)

    override fun saveFingerPrintState(state: Boolean): Flow<Boolean> = flow {
        appRepository.saveFingerPrintState(state)
        emit(state)
    }.flowOn(Dispatchers.IO)

    override fun checkFingerPrintState(): Flow<Boolean> = flow {
        emit(appRepository.getFingerPrintState()  )
    }.flowOn(Dispatchers.IO)

}