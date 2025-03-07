package m.a.nobahar.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.vazir_bold
import nobahar.shared.generated.resources.vazir_light
import nobahar.shared.generated.resources.vazir_regular

private val boldFont @Composable get() = org.jetbrains.compose.resources.Font(Res.font.vazir_bold)
private val lightFont @Composable get() = org.jetbrains.compose.resources.Font(Res.font.vazir_light)
private val regularFont @Composable get() = org.jetbrains.compose.resources.Font(Res.font.vazir_regular)
val Typography
    @Composable get() = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily(boldFont),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = FontFamily(regularFont),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.5.sp
        ),
        bodySmall = TextStyle(
            fontFamily = FontFamily(lightFont),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily(boldFont),
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = FontFamily(boldFont),
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily(lightFont),
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )