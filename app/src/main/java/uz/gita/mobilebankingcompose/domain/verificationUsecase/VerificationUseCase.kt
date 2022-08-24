package uz.gita.mobilebankingcompose.domain.verificationUsecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebankingcompose.data.model.ResultData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpVerifyData

interface VerificationUseCase {
    fun signUpVerify(data: SignUpVerifyData): Flow<ResultData<Unit>>
}