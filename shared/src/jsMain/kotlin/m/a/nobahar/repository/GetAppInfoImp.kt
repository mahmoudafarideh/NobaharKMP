package m.a.nobahar.repository

import m.a.nobahar.api.helper.GetAppInfo

class GetAppInfoImp : GetAppInfo {
    override val version: Long = 1
    override val market: String = "GooglePlay"
}