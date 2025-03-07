package m.a.nobahar

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import m.a.nobahar.ui.main.MainContent
import m.a.nobahar.ui.shared.ui.LocalWindowSize
import org.jetbrains.skiko.wasm.onWasmReady


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
fun main() {
    onWasmReady {
        ComposeViewport(document.body!!) {
            CompositionLocalProvider(LocalWindowSize provides calculateWindowSizeClass()) {
                MainContent()
            }
        }
    }
}