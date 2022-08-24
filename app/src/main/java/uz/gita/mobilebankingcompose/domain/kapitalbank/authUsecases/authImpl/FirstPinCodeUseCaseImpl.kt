package uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.authImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebankingcompose.data.repository.AppRepository
import uz.gita.mobilebankingcompose.data.repository.auth.AuthRepository
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.FirstPinCodeUseCase
import javax.inject.Inject
import kotlin.math.floor

class FirstPinCodeUseCaseImpl @Inject constructor(
    private val appRepository: AppRepository
    ) : FirstPinCodeUseCase {
    override fun savePinCode(code: String): Flow<Unit> = flow {
        emit(appRepository.savePinCode(code))
    }.flowOn(Dispatchers.IO)

}