package uz.gita.mobilebankingcompose.di

import android.content.Context
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.android.compose.AppReducer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebankingcompose.navigation.AppGraph
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModoModule {
    @[Provides Singleton]
    fun privideModo(@ApplicationContext context: Context):Modo = Modo(reducer = AppReducer(context))

    @Singleton
    @Provides
    fun getGraph(): AppGraph = AppGraph()
}