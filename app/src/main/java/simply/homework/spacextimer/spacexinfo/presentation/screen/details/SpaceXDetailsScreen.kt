package simply.homework.spacextimer.spacexinfo.presentation.screen.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simply.homework.spacextimer.spacexinfo.data.ext.appendItemNameAndDescription
import simply.homework.spacextimer.spacexinfo.data.ext.countTime
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfoItem
import simply.homework.spacextimer.spacexinfo.presentation.composable.actionbar.SpaceXActionBar
import simply.homework.spacextimer.spacexinfo.presentation.composable.pager.ImagePagerWithIndicator
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpaceXDetailsScreen(modifier: Modifier, viewModel: SpaceXInfoMVIViewModel) {
    val selectedItem = viewModel.viewState.value.selectedEvent
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {

            stickyHeader(key = "action_bar") {
                //TopAppBar
                SpaceXActionBar(
                    title = viewModel.viewState.value.selectedEvent?.name ?: "ERROR",
                    icon = Icons.AutoMirrored.Filled.ArrowBack
                ) {
                    viewModel.setEvent(InfoContract.Event.ReturnBackFromDetailScreen)
                }
            }

            item {
                ScreenContent(
                    modifier = modifier, selectedItem = selectedItem, viewModel = viewModel
                )
            }
        }
    }
}


@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier, selectedItem: DomainInfoItem?, viewModel: SpaceXInfoMVIViewModel
) {
    val remainingTime = viewModel.viewState.value.selectedEventTimerValue
    if (selectedItem != null) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // image pager
                ImagePagerWithIndicator(
                    imageUrls = selectedItem.flickr_images,
                )

                Text(
                    text = buildAnnotatedString {
                        appendItemNameAndDescription(
                            name = selectedItem.name,
                            description = "${selectedItem.company}\n" +
                                    "Launch date ${remainingTime.countTime()} \n" +
                                    "First flight:${selectedItem.first_flight}"
                        )
                    }, modifier = Modifier.padding(4.dp)
                )
            }

        }

    } else {
        Text(
            text = "No data available",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
    }
}