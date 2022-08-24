package uz.gita.mobilebankingcompose.app

import android.app.Application
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.gita.mobilebankingcompose.BuildConfig

@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}