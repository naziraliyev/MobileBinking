package uz.gita.mobilebankingcompose.data.repository.auth

import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.data.model.MessageData
import uz.gita.mobilebankingcompose.data.model.ResultData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpVerifyData
import uz.gita.mobilebankingcompose.data.model.success
import uz.gita.mobilebankingcompose.data.network.authApi.AuthApi
import uz.gita.mobilebankingcompose.data.pref.MySharedPref
import uz.gita.mobilebankingcompose.data.repository.Mapper
import uz.gita.mobilebankingcompose.data.repository.Mapper.toRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val pref: MySharedPref
) : AuthRepository {
    override suspend fun signUp(data: SignUpData): ResultData<Unit> {
        val signUpRequest = Mapper.run { data.toRequest() }
        val response = authApi.signUp(signUpRequest)
        return when (response.code()) {
            200 -> {
                val token = response.body()!!.token
                pref.tempToken = token
                ResultData.Success(Unit)
            }
            403->{
                val message = response.message()
                ResultData.Fail(message = MessageData.Text(message))
            }
            401->{
                ResultData.Fail(message = MessageData.Resource(R.string.token_is_wrong))
            }
            else->{
                ResultData.Fail(message = MessageData.Resource(R.string.erroMessage))
            }
        }
    }

    override suspend fun signUpVerify(data: SignUpVerifyData): ResultData<Unit> {
        val response =authApi.signUpVerify(token = "Bearer ${pref.tempToken}", request = data.toRequest())
        return when(response.code()){
            200->{
                val tokens= response.body()!!
                pref.tempToken = ""
                pref.accessToken = tokens.accessToken
                pref.refreshToken = tokens.refreshToken
                ResultData.Success(Unit)
            }
            403->{
                val message = response.message()
                ResultData.Fail(message = MessageData.Text(message))
            }
            else->{
                ResultData.Fail(message = MessageData.Resource(R.string.erroMessage))
            }
        }
    }
}