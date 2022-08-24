package uz.gita.mobilebankingcompose.data.repository

import uz.gita.mobilebankingcompose.data.model.LanguageModel
import uz.gita.mobilebankingcompose.data.pref.MySharedPref
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val sharedPref: MySharedPref
) : AppRepository {
    override suspend fun isFirstLaunch(): Boolean = sharedPref.isFirstLaunch
    override suspend fun dismissFirstLaunch() {
        sharedPref.isFirstLaunch = false
    }

    override suspend fun savePinCode(code: String) {
        sharedPref.pinCode = code
    }

    override suspend fun getPinCode(): String = sharedPref.pinCode

    override suspend fun saveFingerPrintState(state: Boolean) {
        sharedPref.fingerPrintState = state
    }

    override suspend fun getFingerPrintState(): Boolean = sharedPref.fingerPrintState

    override suspend fun languagesList(): List<String> =
        listOf(LanguageModel.RU.value, LanguageModel.UZ.value, LanguageModel.EN.value)

    override suspend fun currentLanguage(): String = sharedPref.appLanguage

    override suspend fun setLanguage(language: String) =
        kotlin.run { sharedPref.appLanguage = language }

    override suspend fun setPinCode(code: String) {
        sharedPref.pinCodeApelsin = code
    }

    override suspend fun getPinCodeApelsin(): String = sharedPref.pinCodeApelsin

    override suspend fun setIsLoggedIn(state: Boolean) {
        sharedPref.isLoggedIn = state
    }

    override suspend fun getIsLoggedIn(): Boolean = sharedPref.isLoggedIn

    override suspend fun setFingerPrintState(state: Boolean) {
        sharedPref.pinCodeApelsinFingerPrint = state
    }

    override suspend fun getFingerPrintStateApelsin(): Boolean =
        sharedPref.pinCodeApelsinFingerPrint
}