package uz.gita.mobilebankingcompose.navigation

import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.back
import com.github.terrakok.modo.forward
import com.github.terrakok.modo.replace
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.ui.auth.language.LanguageScreenDirection
import uz.gita.mobilebankingcompose.ui.auth.login.LoginScreenDirection
import uz.gita.mobilebankingcompose.ui.auth.pincode.PinCodeDirection
import uz.gita.mobilebankingcompose.ui.auth.pincode.splashPinCodeScreen.SplashPinCodeDirection
import uz.gita.mobilebankingcompose.ui.auth.privacy.PrivacyDirection
import uz.gita.mobilebankingcompose.ui.splash.SplashScreenDirection
import uz.gita.mobilebankingcompose.ui.auth.verify.VerifyScreenDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.passwordScreen.PasswordDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.passwordScreen.PasswordScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.FirstPinCodeDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.FirstPinCodeScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat.RepeatPinCodeDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat.RepeatPinCodeScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.privacyScreen.PrivacyDirectionApelsin
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.privacyScreen.PrivacyScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.signup.SignUpDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.signup.SignUpScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.splashscreen.SplashDiractionApelsin
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.verifyScreen.VerifyDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.verifyScreen.VerifyScreenApelsin
import uz.gita.mobilebankingcompose.ui.signin.changepassword.ChangePasswordDirection
import uz.gita.mobilebankingcompose.ui.signin.recovery.RecoveryDirection
import uz.gita.mobilebankingcompose.ui.signin.signin.SigInDirection
import javax.inject.Inject
import kotlin.math.atan2

class AppNavigation @Inject constructor(private val modo: Modo, private val graph: AppGraph) {

    val splashDirectionApelsin = object :SplashDiractionApelsin{
        override fun splashToPinCode(state:String) {
            modo.replace(graph.repeatPinCodeApelsin(state = state))
        }

        override fun splashToAuthApelsin() {
            modo.replace(graph.signUpApelsin())
        }

    }

    val signUpDirectionApelsin = object : SignUpDirection {
        override fun navigateToNextScreen(data: SignUpData) {
            modo.forward(graph.verifyApelsin(data))
        }

    }
    val verifyDirectionApelsin = object : VerifyDirection{
        override fun navigateNextScreen() {
            modo.forward(graph.passwordApelsin())
        }

        override fun back() {
            modo.back()
        }

    }
    val passwordDirectionApelsin = object :PasswordDirection{
        override fun navigateToPrivacy() {
           modo.forward(graph.privacyApelsin())
        }

        override fun back() {
            modo.back()
        }

    }
    val privacyDirectionApelsin = object : PrivacyDirectionApelsin{
        override fun navigateToFirstPin() {
            modo.forward(graph.firstPinCodeApelsin())
        }

    }
    val firstPinCodeDirectionApelsin = object :FirstPinCodeDirection{
        override fun navigateToNextPin(state:String) {
            modo.forward(graph.repeatPinCodeApelsin(state = state))
        }

        override fun backPrivacy() {
            modo.back()
        }

    }
    val repeatPinCodeDirectionApelsin = object :RepeatPinCodeDirection{
        override fun navigateToMainScreen() {
            modo.replace(graph.mainScreenApelsin())
        }

        override fun back() {
            modo.back()
        }

    }


///////// gita bank
    val splashScreenDirection = object : SplashScreenDirection {
        override fun navigateToLanguageScreen() {
            modo.replace(graph.languageScreen())
        }

        override fun navigateToSplashPinCodeScreen() {
            modo.replace(graph.splashPinCodeScreen())
        }

        override fun navigateToSignInScreen() {
            modo.replace(graph.signInScreen())
        }
    }

    val languageScreenDirection = object : LanguageScreenDirection {
        override fun navigateToPrivacyScreen() {
            modo.replace(graph.privacyScreen())
        }
        override fun navigateToLoginScreen() {
            modo.replace(graph.loginScreen())
        }
    }
    val privacyDirection = object : PrivacyDirection {
        override fun navigateToSignInScreen() {
            modo.forward(graph.signInScreen())
        }

    }

    val verifyScreenDirection = object : VerifyScreenDirection {
        override fun navigateToPinCode() {
            modo.forward(graph.pinCodeScreen())
        }

        override fun back() {
            modo.back()
        }



    }

    val signInScreenDirection = object : SigInDirection {
        override fun signInToPiCodeScreen() {
            modo.forward(graph.splashPinCodeScreen())
        }

        override fun signInToRecovery() {
            modo.forward(graph.recoveryScreen())
        }

        override fun signInToSignUp() {
            modo.forward(graph.loginScreen())
        }
    }
    val loginScreenDirection = object : LoginScreenDirection {
        override fun navigateToCheckActivityScreen(data:SignUpData) {
            modo.forward(graph.verifyScreen(data))
        }

        override fun back() {
            modo.back()
        }

    }
    val recoveryDirection = object :RecoveryDirection{
        override fun navigateToSignUp(data: SignUpData) {
            modo.forward(graph.verifyScreen(data))
        }
        override fun popBackStack() {
            modo.back()
        }
    }
    val changePasswordDirection = object :ChangePasswordDirection{
        override fun navigateToMainScreen() {
        modo.replace(graph.mainScreen())
        }

        override fun navigateToFingerPrintScreen() {
            modo.replace(graph.fingerPrintScreen())
        }

    }
    val pinCodeDirection = object :PinCodeDirection{
        override fun navigateToMainScreen() {
            modo.replace(graph.mainScreen())
        }

        override fun navigateToFingerPrintScreen() {
            modo.replace(graph.fingerPrintScreen())
        }

    }
    val splashPinCodeDirection = object : SplashPinCodeDirection {

        override fun navigateToMainScreen() {
            modo.replace(graph.mainScreen())
        }

    }
}