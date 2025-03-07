package m.a.nobahar.android.ui.widget.widget

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import m.a.nobahar.android.ui.widget.components.WidgetContent
import m.a.nobahar.android.ui.widget.viewmodel.PoemWidgetViewModel

//private val destinationKey = ActionParameters.Key<String>(
//    KEY_DESTINATION
//)

class PoemVerseWidget(
    private val viewModel: PoemWidgetViewModel
) : GlanceAppWidget() {

    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        provideContent {
            val state = viewModel.state.collectAsState().value

            WidgetContent(
                state = state,
                onRetryClick = { viewModel.retryClicked() },
                onRefreshClick = { viewModel.refreshClicked() },
                onPoemClick = null/*viewModel.currentPoem?.let {
                    AppMetricaAgent.log(
                        PoemFromWidgetScreenEvent(it.id)
                    )
                    val parameters =
                        actionParametersOf(destinationKey to PoemRoute(it.id))
                    actionStartActivity<MainActivity>(parameters)
                }*/
            )
        }
    }
}