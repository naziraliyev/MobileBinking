package uz.gita.mobilebankingcompose.data.repository

import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpVerifyData
import uz.gita.mobilebankingcompose.data.network.request.SignUpRequest
import uz.gita.mobilebankingcompose.data.network.request.SignUpVerifyRequest

object Mapper {

    fun SignUpData.toRequest() = SignUpRequest(
        firstName = firstname,
        lastName = lastname,
        phone = phoneNumber,
        password = password

    )
    fun SignUpVerifyData.toRequest() = SignUpVerifyRequest(code = code)
}
