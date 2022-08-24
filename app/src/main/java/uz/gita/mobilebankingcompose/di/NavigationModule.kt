package uz.gita.mobilebankingcompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebankingcompose.navigation.AppNavigation
import uz.gita.mobilebankingcompose.ui.auth.language.LanguageScreenDirection
import uz.gita.mobilebankingcompose.ui.auth.login.LoginScreenDirection
import uz.gita.mobilebankingcompose.ui.auth.pincode.PinCodeDirection
import uz.gita.mobilebankingcompose.ui.auth.pincode.splashPinCodeScreen.SplashPinCodeDirection
import uz.gita.mobilebankingcompose.ui.auth.privacy.PrivacyDirection
import uz.gita.mobilebankingcompose.ui.auth.verify.VerifyScreenDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.passwordScreen.PasswordDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.FirstPinCodeDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat.RepeatPinCodeDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat.RepeatPinCodeScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.privacyScreen.PrivacyDirectionApelsin
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.privacyScreen.PrivacyScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.signup.SignUpDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.splashscreen.SplashDiractionApelsin
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.verifyScreen.VerifyDirection
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.verifyScreen.VerifyScreenApelsin
import uz.gita.mobilebankingcompose.ui.signin.changepassword.ChangePasswordDirection
import uz.gita.mobilebankingcompose.ui.signin.recovery.RecoveryDirection
import uz.gita.mobilebankingcompose.ui.signin.signin.SigInDirection
import uz.gita.mobilebankingcompose.ui.splash.SplashScreenDirection
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {
    @[Provides Singleton]
    fun provideSplashScreenApelsin(appNavigation: AppNavigation): SplashDiractionApelsin =
        appNavigation.splashDirectionApelsin

    @[Provides Singleton]
    fun provideSignUpScreenApelsin(appNavigation: AppNavigation): SignUpDirection =
        appNavigation.signUpDirectionApelsin

    @[Provides Singleton]
    fun provideVerifyScreenApelsin(appNavigation: AppNavigation): VerifyDirection =
        appNavigation.verifyDirectionApelsin

    @[Provides Singleton]
    fun providePasswordScreenApelsin(appNavigation: AppNavigation): PasswordDirection =
        appNavigation.passwordDirectionApelsin
    @[Provides Singleton]
    fun provideFirstPinCodeScreenApelsin(appNavigation: AppNavigation): FirstPinCodeDirection =
        appNavigation.firstPinCodeDirectionApelsin
    @[Provides Singleton]
    fun provideRepeatPinCodeScreenApelsin(appNavigation: AppNavigation): RepeatPinCodeDirection =
        appNavigation.repeatPinCodeDirectionApelsin
    @[Provides Singleton]
    fun providePrivacyScreenApelsin(appNavigation: AppNavigation): PrivacyDirectionApelsin =
        appNavigation.privacyDirectionApelsin

    ////// GitaBank
    @Provides
    @Singleton
    fun provideSplashScreenDirection(appNavigation: AppNavigation): SplashScreenDirection =
        appNavigation.splashScreenDirection

    @Provides
    @Singleton
    fun languageScreenDirection(appNavigation: AppNavigation): LanguageScreenDirection =
        appNavigation.languageScreenDirection

    @Provides
    @Singleton
    fun loginScreenDirection(appNavigation: AppNavigation): LoginScreenDirection =
        appNavigation.loginScreenDirection

    @Provides
    @Singleton
    fun verifyScreenDirection(appNavigation: AppNavigation): VerifyScreenDirection =
        appNavigation.verifyScreenDirection

    @Provides
    @Singleton
    fun privacyScreenDirection(appNavigation: AppNavigation): PrivacyDirection =
        appNavigation.privacyDirection

    @Provides
    @Singleton
    fun singInScreenDirection(appNavigation: AppNavigation): SigInDirection =
        appNavigation.signInScreenDirection

    @Provides
    @Singleton
    fun recoveryScreenDirection(appNavigation: AppNavigation): RecoveryDirection =
        appNavigation.recoveryDirection

    @Provides
    @Singleton
    fun changePasswordDirection(appNavigation: AppNavigation): ChangePasswordDirection =
        appNavigation.changePasswordDirection

    @Provides
    @Singleton
    fun pinCodeDirection(appNavigation: AppNavigation): PinCodeDirection =
        appNavigation.pinCodeDirection

    @Provides
    @Singleton
    fun splashPinCodeDiraction(appNavigation: AppNavigation): SplashPinCodeDirection =
        appNavigation.splashPinCodeDirection
}
