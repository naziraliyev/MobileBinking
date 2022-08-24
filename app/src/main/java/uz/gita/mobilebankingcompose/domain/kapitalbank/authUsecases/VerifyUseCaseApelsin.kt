package uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebankingcompose.data.model.ResultData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpVerifyData

interface VerifyUseCaseApelsin {
    fun signUpVerify(data: SignUpVerifyData): Flow<ResultData<Unit>>
}