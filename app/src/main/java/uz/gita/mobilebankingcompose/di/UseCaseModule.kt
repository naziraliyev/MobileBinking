package uz.gita.mobilebankingcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebankingcompose.domain.kapitalbank.appUsecase.SplashUseCaseApelsin
import uz.gita.mobilebankingcompose.domain.kapitalbank.appUsecase.impl.SplashUseCaseApelsinImpl
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.FirstPinCodeUseCase
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.RepeatPinCodeUseCase
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.SignUpUseCaseApelsin
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.VerifyUseCaseApelsin
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.authImpl.FirstPinCodeUseCaseImpl
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.authImpl.RepeatPinCodeUseCaseImpl
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.authImpl.SignUpUseCaseApelsinImpl
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.authImpl.VerifyUseCaseApelsinImpl
import uz.gita.mobilebankingcompose.domain.languageUseCase.LanguageUseCase
import uz.gita.mobilebankingcompose.domain.languageUseCase.LanguageUseCaseImpl
import uz.gita.mobilebankingcompose.domain.picodeUseCase.PinCodeUseCase
import uz.gita.mobilebankingcompose.domain.picodeUseCase.PinCodeUseCaseImpl
import uz.gita.mobilebankingcompose.domain.signUpUseCase.SignUpUseCase
import uz.gita.mobilebankingcompose.domain.signUpUseCase.SignUpUseCaseImpl
import uz.gita.mobilebankingcompose.domain.splashUscase.SplashUseCase
import uz.gita.mobilebankingcompose.domain.splashUscase.SplashUseCaseImpl
import uz.gita.mobilebankingcompose.domain.verificationUsecase.VerificationUseCase
import uz.gita.mobilebankingcompose.domain.verificationUsecase.VerificationUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindSplashUseCaseApelsin(impl: SplashUseCaseApelsinImpl): SplashUseCaseApelsin

    @Binds
    fun bindSignUpUseCaseApelsin(impl: SignUpUseCaseApelsinImpl): SignUpUseCaseApelsin
    @Binds
    fun bindVerifyUseCaseApelsin(impl: VerifyUseCaseApelsinImpl): VerifyUseCaseApelsin
    @Binds
    fun bindFirstPinCodeUseCaseApelsin(impl: FirstPinCodeUseCaseImpl): FirstPinCodeUseCase
    @Binds
    fun binRepeatPinCodeUseCaseApelsin(impl: RepeatPinCodeUseCaseImpl): RepeatPinCodeUseCase


    ////gitabank
    @Binds
    fun bindSplashUseCase(impl: SplashUseCaseImpl): SplashUseCase

    @Binds
    fun bindLanguageUseCase(impl: LanguageUseCaseImpl): LanguageUseCase

    @Binds
    fun bindPinCodeUseCase(impl: PinCodeUseCaseImpl): PinCodeUseCase

    @Binds
    fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    fun bindVerificationUseCase(impl: VerificationUseCaseImpl): VerificationUseCase

}