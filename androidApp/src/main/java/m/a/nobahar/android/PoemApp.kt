package m.a.nobahar.android

import android.app.Application
import m.a.nobahar.analytics.AppMetricaAgent
import m.a.nobahar.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PoemApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppMetricaAgent.startAppMetrica(this)
        startKoin {
            androidLogger()
            androidContext(this@PoemApp)
            modules(koinModules)
        }
    }
}