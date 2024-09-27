package simply.homework.spacextimer.spacexinfo.presentation.comp.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import simply.homework.spacextimer.commonpresentation.ui.theme.spacexFontFamily
import simply.homework.spacextimer.spacexinfo.data.ext.appendItemNameAndDescription
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfoItem

@Composable
fun SpaceXRaceSelectedItemDetails(
    selectedItem: DomainInfoItem, remainingTimerValue: String = "17:48:59"
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Image with Coil
                Image(
                    painter = rememberAsyncImagePainter(model = selectedItem.flickr_images.random()),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.15f), Color.Black.copy(alpha = 0.4f)
                                ), startY = 0f, endY = 300f
                            )
                        ), contentAlignment = Alignment.Center
                ) {
                    Column {
                        // Timer Text in the center
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = remainingTimerValue,
                            fontSize = 36.sp,
                            fontFamily = spacexFontFamily,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Text(
                text = buildAnnotatedString {
                    appendItemNameAndDescription(
                        name = selectedItem.name, description = selectedItem.description
                    )
                }, modifier = Modifier.padding(4.dp)
            )
        }
    }
}
