package uz.gita.mobilebankingcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebankingcompose.data.repository.AppRepository
import uz.gita.mobilebankingcompose.data.repository.AppRepositoryImpl
import uz.gita.mobilebankingcompose.data.repository.auth.AuthRepository
import uz.gita.mobilebankingcompose.data.repository.auth.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppRepositoryModule {

    @[Binds Singleton]
    fun bindAppRepository(impl: AppRepositoryImpl):AppRepository

    @[Binds Singleton]
    fun bindAuthRepository(impl: AuthRepositoryImpl):AuthRepository
}