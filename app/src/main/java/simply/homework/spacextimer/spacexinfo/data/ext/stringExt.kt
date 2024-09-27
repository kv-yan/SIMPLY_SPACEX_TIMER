package simply.homework.spacextimer.spacexinfo.data.ext

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.sp
import simply.homework.spacextimer.commonpresentation.ui.theme.spacexFontFamily

fun AnnotatedString.Builder.appendItemNameAndDescription(
    name: String,
    description: String
) {
    append(name)
    addStyle(
        style = SpanStyle(fontSize = 18.sp, fontFamily = spacexFontFamily),
        start = 0,
        end = name.length
    )

    append("\n")

    append(description)
    addStyle(
        style = SpanStyle(fontSize = 12.sp, fontFamily = spacexFontFamily),
        start = name.length + 1, // Start after the newline
        end = name.length + 1 + description.length
    )
}