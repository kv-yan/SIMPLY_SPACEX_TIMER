package simply.homework.spacextimer.spacexinfo.presentation.comp.items

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
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfoItem
import simply.homework.spacextimer.spacexinfo.presentation.comp.pager.ImagePagerWithIndicator

@Composable
fun SpaceXRaceItem(item: DomainInfoItem) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.DarkGray.copy(alpha = 0.5f)
    ) {
        Column {
            ImagePagerWithIndicator(imageUrls = item.flickr_images)


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Details",
                    color = Color.Cyan,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                )
            }
        }
    }


}