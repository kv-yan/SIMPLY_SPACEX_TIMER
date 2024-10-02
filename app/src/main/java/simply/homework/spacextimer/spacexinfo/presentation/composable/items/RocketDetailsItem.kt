package simply.homework.spacextimer.spacexinfo.presentation.composable.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RocketDetailsItem(
    modifier: Modifier = Modifier, descriptionTitle: String, description: String, index: Int
) {
    val backgroundColor = if (index % 2 == 0) Color.LightGray else Color.Transparent

    Surface(color = backgroundColor) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = descriptionTitle, modifier = Modifier.fillMaxWidth(0.4f))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = description, modifier = Modifier.fillMaxWidth())

        }

    }
}


@Preview
@Composable
private fun Preview() {
    val list = listOf(1, 2, 3, 4, 5, 6)

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(list) { index, item ->
            RocketDetailsItem(
                descriptionTitle = "Title",
                description = "Description",
                index = index
            )
        }
    }
}