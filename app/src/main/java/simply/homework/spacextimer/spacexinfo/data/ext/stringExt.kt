package simply.homework.spacextimer.spacexinfo.data.ext

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.sp
import simply.homework.spacextimer.commonpresentation.ui.theme.spacexFontFamily

fun AnnotatedString.Builder.appendItemNameAndDescription(
    name: String, isShowingTimer: Boolean = false, description: String
) {
    append(name)
    addStyle(
        style = SpanStyle(fontSize = 18.sp, fontFamily = spacexFontFamily),
        start = 0,
        end = name.length
    )

    append("\n")
    val startAfterName = name.length + 1
    if (isShowingTimer) {
        append("Will launch in: ")

        val timerText = "Will launch in: $description"
        val totalTextLength = startAfterName + timerText.length

        addStyle(
            style = SpanStyle(fontSize = 12.sp, fontFamily = spacexFontFamily),
            start = startAfterName,
            end = totalTextLength
        )

        append(description)

        addStyle(
            style = SpanStyle(fontSize = 16.sp, fontFamily = spacexFontFamily),
            start = totalTextLength - description.length,
            end = totalTextLength
        )
    } else {
        append(description)
        addStyle(
            style = SpanStyle(fontSize = 12.sp, fontFamily = spacexFontFamily),
            start = startAfterName,
            end = startAfterName + description.length
        )
    }
}
