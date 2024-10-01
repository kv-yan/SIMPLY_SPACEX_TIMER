package simply.homework.spacextimer.spacexinfo.presentation.composable.timer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import simply.homework.spacextimer.commonpresentation.ui.theme.spacexFontFamily
import java.util.concurrent.TimeUnit

@Composable
fun TakeoffTimer(timeMillis: MutableState<Long>) {
    val remainingTime = timeMillis.value
    val years = TimeUnit.MILLISECONDS.toDays(remainingTime) / 365
    val months = (TimeUnit.MILLISECONDS.toDays(remainingTime) % 365) / 30
    val days = (TimeUnit.MILLISECONDS.toDays(remainingTime) % 365) % 30
    val hours = (TimeUnit.MILLISECONDS.toHours(remainingTime) % 24)
    val minutes = (TimeUnit.MILLISECONDS.toMinutes(remainingTime) % 60)
    val seconds = (TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60)

    // Build the countdown string based on non-zero values
    val countdownString = buildString {
        if (years > 0) append("${years}y,")
        if (months > 0) append("${months}m:,")
        if (days > 0) append("${days}d:")
        if (hours > 0) append("${hours}h:")
        if (minutes > 0) append("${minutes}m:")
        append("${seconds}s")
    }.trimEnd(':', ' ')

    println("Countdown: $countdownString")

    Text(
        text = countdownString, modifier = Modifier.fillMaxWidth(), fontSize = 36.sp,
        fontFamily = spacexFontFamily,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}
