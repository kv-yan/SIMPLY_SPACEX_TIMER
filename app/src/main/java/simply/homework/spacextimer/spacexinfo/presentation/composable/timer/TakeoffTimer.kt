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
import simply.homework.spacextimer.spacexinfo.data.ext.countTime
import java.util.concurrent.TimeUnit

@Composable
fun TakeoffTimer(
    timeMillis: MutableState<Long>
) {
    val countdownString = timeMillis.countTime()

    Text(
        text = countdownString,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 36.sp,
        fontFamily = spacexFontFamily,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}