package simply.homework.spacextimer.spacexinfo.presentation.composable.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simply.homework.spacextimer.spacexinfo.domain.model.Rocket
import simply.homework.spacextimer.spacexinfo.domain.model.RocketImage
import simply.homework.spacextimer.spacexinfo.presentation.composable.pager.ImagePagerWithIndicator

@Composable
fun SpaceXRaceItem(
    item: Rocket,
    onItemClick: (Rocket) -> Unit,
    onDetailsClick: (Rocket) -> Unit
) {

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.DarkGray.copy(alpha = 0.5f),
        onClick = { onItemClick(item) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            ImagePagerWithIndicator(imageUrls = item.imageList, modifier = Modifier)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(0.8f)
                )
                Text(
                    text = "Details",
                    color = Color.Cyan,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .weight(0.2f)
                        .clickable {
                            onDetailsClick(item)
                        }
                )
            }
        }
    }


}