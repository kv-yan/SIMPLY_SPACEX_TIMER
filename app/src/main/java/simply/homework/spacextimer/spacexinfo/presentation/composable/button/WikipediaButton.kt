package simply.homework.spacextimer.spacexinfo.presentation.composable.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simply.homework.spacextimer.R

@Composable
fun WikipediaButton(modifier: Modifier = Modifier, clickAction: () -> Unit) {
    Surface(
        onClick = { clickAction() },
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 8.dp,
        modifier = modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_wikipedia),
                contentDescription = null,
                modifier = Modifier.size(32.dp)

            )

            Text(
                text = "Wikipedia",
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WikipediaButton {

    }
}