package m.a.nobahar.api.repository

import m.a.nobahar.api.contract.SplashApi
import m.a.nobahar.api.helper.GetAppInfo
import m.a.nobahar.config.PrefKeys
import m.a.nobahar.domain.model.HomeCommunication
import m.a.nobahar.domain.repository.HomeCommunicationRepository
import m.a.nobahar.domain.repository.SplashRepository
import m.a.nobahar.domain.storage.LocalStorage
import m.a.nobahar.domain.storage.optional
import m.a.nobahar.ui.logInfo

class SplashRepositoryImp(
    private val splashApi: SplashApi,
    private val localStorage: LocalStorage,
    private val homeCommunicationRepository: HomeCommunicationRepository,
    private val getAppInfo: GetAppInfo
) : SplashRepository {

    override suspend fun getSplash() {
        val firebaseToken = localStorage.optional<String>(PrefKeys.FirebaseToken)
        val deviceId = localStorage.optional<Long>(PrefKeys.DeviceId)
        logInfo("Splash", "${deviceId.getValue()} ${firebaseToken.getValue()}")
        val splashData = splashApi.getSplash(
            firebaseToken.getValue(),
            getAppInfo.version,
            deviceId.getValue()
        )
        splashData.deviceId?.let {
            deviceId.updateValue(it)
        }

        when {
            splashData.hasNewVersion -> {
                homeCommunicationRepository.setCommunication(
                    HomeCommunication.AppUpdate
                )
            }

            splashData.homeBanner != null -> {
                homeCommunicationRepository.setCommunication(
                    HomeCommunication.HomeBanner(
                        splashData.homeBanner.bannerUrl,
                        splashData.homeBanner.actionUrl,
                        splashData.homeBanner.aspect
                    )
                )
            }
        }
    }
}