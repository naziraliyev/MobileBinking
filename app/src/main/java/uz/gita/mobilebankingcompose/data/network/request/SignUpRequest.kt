package uz.gita.mobilebankingcompose.data.network.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignUpRequest (
    val firstName: String,
    val lastName: String,
    val password: String,
    val phone: String
)