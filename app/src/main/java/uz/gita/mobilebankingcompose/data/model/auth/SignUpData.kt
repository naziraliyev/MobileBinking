package uz.gita.mobilebankingcompose.data.model.auth

import java.io.Serializable

data class SignUpData (
    val firstname: String,
    val lastname: String,
    val password: String,
    val phoneNumber: String
):Serializable