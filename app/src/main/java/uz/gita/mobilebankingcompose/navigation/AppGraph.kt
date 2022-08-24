package uz.gita.mobilebankingcompose.navigation

import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.ui.auth.fingerPrintScreen.FingerPrintScreen
import uz.gita.mobilebankingcompose.ui.auth.language.LanguageScreen
import uz.gita.mobilebankingcompose.ui.auth.login.LoginScreen
import uz.gita.mobilebankingcompose.ui.auth.pincode.PinCodeScreen
import uz.gita.mobilebankingcompose.ui.auth.pincode.splashPinCodeScreen.SplashPinCodeScreen
import uz.gita.mobilebankingcompose.ui.auth.privacy.PrivacyPolicyScreen
import uz.gita.mobilebankingcompose.ui.auth.verify.VerifyScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.passwordScreen.PasswordScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.passwordScreen.PasswordScreenContent
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.FirstPinCodeScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat.RepeatPinCodeScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.privacyScreen.PrivacyScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.signup.SignUpScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.splashscreen.SplashScreenApelsin
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.verifyScreen.VerifyScreenApelsin
import uz.gita.mobilebankingcompose.ui.kapitalbank.main.main.MainScreenApelsin
import uz.gita.mobilebankingcompose.ui.mainpages.MainScreen
import uz.gita.mobilebankingcompose.ui.signin.changepassword.ChangePasswordScreen
import uz.gita.mobilebankingcompose.ui.signin.recovery.RecoveryScreen
import uz.gita.mobilebankingcompose.ui.signin.signin.SignInScreen

class AppGraph {

    fun signUpApelsin() = SignUpScreen()
    fun verifyApelsin(data: SignUpData) = VerifyScreenApelsin(data=data)
    fun passwordApelsin() = PasswordScreen()
    fun privacyApelsin() = PrivacyScreen()
    fun firstPinCodeApelsin() = FirstPinCodeScreen()
    fun repeatPinCodeApelsin(state:String) = RepeatPinCodeScreen(state)
    fun mainScreenApelsin() = MainScreenApelsin()

    ////gita bank
    fun languageScreen() = LanguageScreen()
    fun privacyScreen() = PrivacyPolicyScreen()
    fun loginScreen() = LoginScreen()
    fun verifyScreen(data: SignUpData) = VerifyScreen(data = data)
    fun pinCodeScreen() = PinCodeScreen()
    fun signInScreen() = SignInScreen()
    fun recoveryScreen() = RecoveryScreen()
    fun changePasswordScreen() = ChangePasswordScreen()
    fun splashPinCodeScreen() = SplashPinCodeScreen()

    fun fingerPrintScreen() = FingerPrintScreen()
    fun mainScreen() = MainScreen()

}