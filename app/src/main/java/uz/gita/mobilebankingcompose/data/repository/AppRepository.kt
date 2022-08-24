package uz.gita.mobilebankingcompose.data.repository

interface AppRepository {
    suspend fun isFirstLaunch(): Boolean

    suspend fun dismissFirstLaunch()

    suspend fun savePinCode(code: String)

    suspend fun getPinCode(): String

    suspend fun saveFingerPrintState(state: Boolean)

    suspend fun getFingerPrintState(): Boolean

    suspend fun languagesList(): List<String>

    suspend fun currentLanguage(): String

    suspend fun setLanguage(language: String)

    //Apelsin

    suspend fun setPinCode(code: String)
    suspend fun getPinCodeApelsin(): String

    suspend fun setIsLoggedIn(state: Boolean)
    suspend fun getIsLoggedIn(): Boolean

    suspend fun setFingerPrintState(state: Boolean)
    suspend fun getFingerPrintStateApelsin(): Boolean

}