package uz.gita.mobilebankingcompose.data.pref

import android.content.Context
import uz.gita.mobilebankingcompose.data.model.LanguageModel
import uz.gita.mobilebankingcompose.utils.SharedPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySharedPref
@Inject constructor(context: Context): SharedPreference(context) {
    //aut apelsin

    var isLoggedIn:Boolean by BooleanPreference(false)
    var pinCodeApelsin:String by StringPreference("")
    var pinCodeApelsinFingerPrint:Boolean by BooleanPreference(false)

    //language
     var isFirstLaunch:Boolean by BooleanPreference(true)
    var appLanguage: String by StringPreference(LanguageModel.UZ.value)

    //auth
    var tempToken: String by StringPreference("")

    var accessToken: String by StringPreference("")

    var refreshToken: String by StringPreference("")

    //pincode
    var pinCode: String by StringPreference("")
    var fingerPrintState: Boolean by BooleanPreference(false)



}