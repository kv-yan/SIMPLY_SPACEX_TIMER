package simply.homework.spacextimer.spacexinfo.data.ext

import androidx.compose.runtime.MutableState
import java.util.concurrent.TimeUnit

fun MutableState<Long>.countTime(): String {
    val remainingTime = this.value
    val years = TimeUnit.MILLISECONDS.toDays(remainingTime) / 365
    val months = (TimeUnit.MILLISECONDS.toDays(remainingTime) % 365) / 30
    val days = (TimeUnit.MILLISECONDS.toDays(remainingTime) % 365) % 30
    val hours = (TimeUnit.MILLISECONDS.toHours(remainingTime) % 24)
    val minutes = (TimeUnit.MILLISECONDS.toMinutes(remainingTime) % 60)
    val seconds = (TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60)

    return buildString {
        if (years > 0) append("${years}y,")
        if (months > 0) append("${months}m:,")
        if (days > 0) append("${days}d:")
        if (hours > 0) append("${hours}h:")
        if (minutes > 0) append("${minutes}m:")
        append("${seconds}s")
    }.trimEnd(':', ' ')

}