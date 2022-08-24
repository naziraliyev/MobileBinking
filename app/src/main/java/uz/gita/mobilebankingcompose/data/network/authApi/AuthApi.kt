package uz.gita.mobilebankingcompose.data.network.authApi

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.mobilebankingcompose.BuildConfig.*
import uz.gita.mobilebankingcompose.data.network.request.SignUpRequest
import uz.gita.mobilebankingcompose.data.network.request.SignUpVerifyRequest
import uz.gita.mobilebankingcompose.data.network.response.SignUpResponse
import uz.gita.mobilebankingcompose.data.network.response.SignUpVerifyResponse

interface AuthApi {


    @POST(SIGN_UP)
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>

    @POST(SIGN_UP_VERIFY)
    suspend fun signUpVerify(
        @Header(AUTHORIZATION) token: String,
        @Body request: SignUpVerifyRequest
    ): Response<SignUpVerifyResponse>


}