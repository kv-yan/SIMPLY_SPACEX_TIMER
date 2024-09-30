package simply.homework.spacextimer.spacexinfo.presentation.composable.items

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
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import simply.homework.spacextimer.spacexinfo.data.ext.appendItemNameAndDescription
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfoItem
import simply.homework.spacextimer.spacexinfo.presentation.composable.timer.TakeoffTimer
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

@Composable
fun SpaceXRaceSelectedItemDetails(
    viewModel: SpaceXInfoMVIViewModel, selectedItem: DomainInfoItem, remainingTimerValue: Long
) {

    viewModel.setEvent(InfoContract.Event.StartSelectedItemCountdownTime)
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
                        TakeoffTimer(remainingTimeMillis = remainingTimerValue)
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
