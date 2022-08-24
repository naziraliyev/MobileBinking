package uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.authImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebankingcompose.data.model.MessageData
import uz.gita.mobilebankingcompose.data.model.ResultData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpVerifyData
import uz.gita.mobilebankingcompose.data.repository.auth.AuthRepository
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.VerifyUseCaseApelsin
import javax.inject.Inject

class VerifyUseCaseApelsinImpl @Inject constructor(private val authRepository: AuthRepository) :
    VerifyUseCaseApelsin {
    override fun signUpVerify(data: SignUpVerifyData): Flow<ResultData<Unit>> = flow {
        emit(authRepository.signUpVerify(data))
    }.catch { ResultData.Fail(message = MessageData.Text("Error $it")) }
        .flowOn(Dispatchers.IO)
}