package m.a.nobahar.ui

import androidx.compose.runtime.Composable
import kotlinx.browser.window

@Composable
actual fun KeepScreenOn(state: Boolean) {

}

@Composable
actual fun BackHandler(enabled: Boolean, onBackClick: () -> Unit) {
    return
}

@Composable
actual fun goToMarket(): () -> Unit {
    return {
        window.open("https://nobahar.app")
    }
}

@Composable
actual fun goToMatnnegarMarket(): () -> Unit {
    return {
        window.open("https://matnnegar.ir/landing")
    }
}

@Composable
actual fun goToInstagram(): () -> Unit {
    return {
        window.open("https://t.me/nobahar.app")
    }
}

@Composable
actual fun goToTelegram(): () -> Unit {
    return {
        window.open("https://t.me/nobahar_app")
    }
}

@Composable
actual fun goToUrl(url: String): () -> Unit {
    return {
        window.open(url)
    }
}

actual fun logInfo(key: String, data: Any?) {
    console.log("$key $data")
}