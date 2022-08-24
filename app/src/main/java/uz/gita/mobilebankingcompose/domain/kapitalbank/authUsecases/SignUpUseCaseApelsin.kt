package uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebankingcompose.data.model.ResultData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData

interface SignUpUseCaseApelsin {
    fun signUpData(data: SignUpData): Flow<ResultData<Unit>>
}