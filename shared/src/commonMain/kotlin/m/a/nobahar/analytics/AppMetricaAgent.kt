package m.a.nobahar.analytics


expect object AppMetricaAgent {
    fun log(event: AnalyticsEvent)
}