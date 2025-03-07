package m.a.nobahar.android.ui.widget.widget

import androidx.glance.appwidget.GlanceAppWidgetReceiver
import org.koin.java.KoinJavaComponent

class PoemVerseWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: PoemVerseWidget by KoinJavaComponent.inject(
        PoemVerseWidget::class.java
    )

}