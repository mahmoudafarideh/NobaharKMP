package m.a.nobahar.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.WindowManager
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import m.a.nobahar.api.helper.GetAppInfo
import org.koin.java.KoinJavaComponent

@Composable
actual fun KeepScreenOn(state: Boolean) {
    val activity = LocalActivity.current
    when (state) {
        true -> {
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }

        else -> {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
}

@Composable
actual fun BackHandler(enabled: Boolean, onBackClick: () -> Unit) {
    androidx.activity.compose.BackHandler(enabled, onBackClick)
}

@Composable
actual fun goToMarket(): () -> Unit {
    val context = LocalContext.current
    return { context.goToMarket() }
}

@Composable
actual fun goToMatnnegarMarket(): () -> Unit {
    val context = LocalContext.current
    return { context.goToMatnnegarMarket() }
}

@Composable
actual fun goToInstagram(): () -> Unit {
    val context = LocalContext.current
    return { context.goToInstagram() }
}

@Composable
actual fun goToTelegram(): () -> Unit {
    val context = LocalContext.current
    return { context.goToTelegram() }
}

internal fun Context.goToMarket(packageValue: String = packageName) {
    val getAppInfo by KoinJavaComponent.inject<GetAppInfo>(GetAppInfo::class.java)
    when (getAppInfo.market.lowercase()) {
        "cafebazaar" -> openCafeBazaar(packageValue)
        "myket" -> openMyket(packageValue)
        "googleplay" -> openGooglePlay(packageValue)
        else -> openCafeBazaar(packageValue)
    }
}

internal fun Context.goToMatnnegarMarket() {
    goToMarket("com.ma.textgraphy")
}

internal fun Context.goToInstagram() {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/_u/nobaharapp"))
    runCatching {
        intent.setPackage("com.instagram.android")
        startActivity(intent)
    }.onFailure {
        intent.setPackage(null)
        startActivity(intent)
    }
}

internal fun Context.goToTelegram() {

    runCatching {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=nobaharapp"))
        intent.setPackage("org.telegram.messenger")
        startActivity(intent)
    }.onFailure {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/nobaharapp"))
        startActivity(intent)
    }
}

private fun Context.openCafeBazaar(packageValue: String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("bazaar://details?id=$packageValue")
        setPackage("com.farsitel.bazaar")
    }.let {
        runCatching {
            startActivity(it)
        }.onFailure {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://cafebazaar.ir/app/$packageValue")
                )
            )
        }
    }
}

private fun Context.openGooglePlay(packageValue: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageValue")))
    } catch (_: Exception) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageValue")
            )
        )
    }
}

private fun Context.openMyket(packageValue: String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("myket://download/$packageValue")
        setPackage("ir.mservices.market")
    }.let {
        runCatching {
            startActivity(it)
        }.onFailure {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://myket.ir/app/$packageValue")
                )
            )
        }
    }
}

@Composable
actual fun goToUrl(url: String): () -> Unit {
    val context = LocalContext.current
    return {
        Intent(Intent.ACTION_VIEW).apply {
            this.data = Uri.parse(url)
        }.let {
            context.startActivity(it)
        }
    }
}

actual fun logInfo(key: String, data: Any?) {

}