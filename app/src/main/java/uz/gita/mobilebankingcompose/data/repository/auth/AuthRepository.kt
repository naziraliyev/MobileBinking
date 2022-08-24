package uz.gita.mobilebankingcompose.data.repository.auth

import retrofit2.Response
import uz.gita.mobilebankingcompose.data.model.ResultData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpVerifyData
import uz.gita.mobilebankingcompose.data.network.request.SignUpRequest

interface AuthRepository {

    suspend fun signUp(data:SignUpData):ResultData<Unit>

    suspend fun signUpVerify(data:SignUpVerifyData):ResultData<Unit>
}