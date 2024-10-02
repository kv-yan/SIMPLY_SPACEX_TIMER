package simply.homework.spacextimer.spacexinfo.presentation.composable.pager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePagerWithIndicator(imageUrls: List<String>, modifier: Modifier = Modifier) {
    val pagerState = remember(imageUrls.size) { PagerState() }
    val currentImageUrls by rememberUpdatedState(newValue = imageUrls)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
    ) {
        // HorizontalPager for images
        HorizontalPager(
            count = currentImageUrls.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize() // Use fillMaxSize to overlay dots on image
        ) { page ->
            Image(
                painter = rememberAsyncImagePainter(model = currentImageUrls[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Dots indicator overlaid on the bottom of the image
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter) // Align dots to the bottom of the image
                .padding(8.dp)
        ) {
            repeat(currentImageUrls.size) { index ->
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
