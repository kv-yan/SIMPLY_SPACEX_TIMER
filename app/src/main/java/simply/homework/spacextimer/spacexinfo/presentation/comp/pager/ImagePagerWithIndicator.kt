package simply.homework.spacextimer.spacexinfo.presentation.comp.pager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePagerWithIndicator(imageUrls: List<String>) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // HorizontalPager for images
        HorizontalPager(
            count = imageUrls.size, state = pagerState, modifier = Modifier.fillMaxWidth()
        ) { page ->
            Image(
                painter = rememberAsyncImagePainter(model = imageUrls[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f),
                contentScale = ContentScale.Crop
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            repeat(imageUrls.size) { index ->
                val color = if (pagerState.currentPage == index) Color.LightGray else Color.DarkGray
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .padding(4.dp)
                        .background(color, CircleShape)
                )
            }
        }
    }
}
