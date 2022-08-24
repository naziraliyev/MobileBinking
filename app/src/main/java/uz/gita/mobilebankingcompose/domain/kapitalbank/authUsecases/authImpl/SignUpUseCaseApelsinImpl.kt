package uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.authImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebankingcompose.data.model.MessageData
import uz.gita.mobilebankingcompose.data.model.ResultData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.data.repository.auth.AuthRepository
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.SignUpUseCaseApelsin
import javax.inject.Inject

class SignUpUseCaseApelsinImpl @Inject constructor(private val authRepository: AuthRepository) :SignUpUseCaseApelsin {
    override fun signUpData(data: SignUpData): Flow<ResultData<Unit>> = flow {
        emit(authRepository.signUp(data))
    }.catch { ResultData.Fail(message = MessageData.Text("Error $it")) }
        .flowOn(Dispatchers.IO)

}