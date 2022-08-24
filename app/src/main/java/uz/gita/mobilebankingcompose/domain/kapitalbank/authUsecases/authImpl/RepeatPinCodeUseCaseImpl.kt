package uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.authImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebankingcompose.data.repository.AppRepository
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.RepeatPinCodeUseCase
import javax.inject.Inject

class RepeatPinCodeUseCaseImpl @Inject constructor(
    private val appRepository: AppRepository
    ) : RepeatPinCodeUseCase {
    override fun savePinCode(code: String): Flow<Unit> = flow {
        emit(appRepository.setPinCode(code))
    }.flowOn(Dispatchers.IO)

    override fun checkPinCode(code: String): Flow<Boolean> = flow {
        emit(appRepository.getPinCodeApelsin()==code)
    }.flowOn(Dispatchers.IO)



}