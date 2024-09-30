package simply.homework.spacextimer.spacexinfo.presentation.composable.timer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import simply.homework.spacextimer.commonpresentation.ui.theme.spacexFontFamily
import java.util.concurrent.TimeUnit

@Composable
fun TakeoffTimer(remainingTimeMillis: Long) {
    val years = TimeUnit.MILLISECONDS.toDays(remainingTimeMillis) / 365
    val months = (TimeUnit.MILLISECONDS.toDays(remainingTimeMillis) % 365) / 30
    val days = (TimeUnit.MILLISECONDS.toDays(remainingTimeMillis) % 365) % 30
    val hours = (TimeUnit.MILLISECONDS.toHours(remainingTimeMillis) % 24)
    val minutes = (TimeUnit.MILLISECONDS.toMinutes(remainingTimeMillis) % 60)
    val seconds = (TimeUnit.MILLISECONDS.toSeconds(remainingTimeMillis) % 60)

    // Build the countdown string based on non-zero values
    val countdownString = buildString {
        if (years > 0) append("$years :, ")
        if (months > 0) append("$months :, ")
        if (days > 0) append("$days : ")
        if (hours > 0) append("$hours : ")
        if (minutes > 0) append("$minutes : ")
        append("$seconds ")
    }.trimEnd(':', ' ')

    Text(
        text = countdownString, modifier = Modifier.fillMaxWidth(), fontSize = 36.sp,
        fontFamily = spacexFontFamily,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}
