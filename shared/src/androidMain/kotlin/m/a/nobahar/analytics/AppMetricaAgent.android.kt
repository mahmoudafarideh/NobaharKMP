package m.a.nobahar.analytics

import android.app.Application

actual object AppMetricaAgent {
    actual fun log(event: AnalyticsEvent) {
    }

    fun startAppMetrica(poemApp: Application) {
    }
}