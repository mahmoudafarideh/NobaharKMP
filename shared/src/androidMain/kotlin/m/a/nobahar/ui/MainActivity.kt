package m.a.nobahar.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import m.a.nobahar.ui.main.MainContent
import m.a.nobahar.ui.shared.ui.LocalWindowSize

class MainActivity : ComponentActivity() {

    private val navigationFlow = MutableStateFlow<Any?>(null)
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { _: Boolean ->
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (
                ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    @OptIn(
        ExperimentalMaterial3WindowSizeClassApi::class,
        ExperimentalMaterial3WindowSizeClassApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIntentDestination()
        askNotificationPermission()
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(LocalWindowSize provides calculateWindowSizeClass(this)) {
                CompositionLocalProvider(LocalActivity provides this) {
                    MainContent()
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        checkIntentDestination()
    }

    private fun checkIntentDestination() {
        intent.extras?.getString(KEY_DESTINATION)?.let { _ ->
            navigationFlow.update {
            }
            intent.removeExtra(KEY_DESTINATION)
            intent.removeExtra(KEY_DESTINATION_ROUTE)
        }
    }

    companion object {
        const val KEY_DESTINATION = "KEY_DESTINATION"
        const val KEY_DESTINATION_ROUTE = "KEY_DESTINATION_ROUTE"
    }
}
