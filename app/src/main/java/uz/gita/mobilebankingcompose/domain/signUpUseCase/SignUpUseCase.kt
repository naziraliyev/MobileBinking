package uz.gita.mobilebankingcompose.domain.signUpUseCase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebankingcompose.data.model.ResultData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData

interface SignUpUseCase {
    fun signUp(data: SignUpData): Flow<ResultData<Unit>>
}