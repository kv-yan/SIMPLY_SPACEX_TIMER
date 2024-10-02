package simply.homework.spacextimer.spacexinfo.presentation.screen.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simply.homework.spacextimer.spacexinfo.data.ext.appendItemNameAndDescription
import simply.homework.spacextimer.spacexinfo.data.ext.countTime
import simply.homework.spacextimer.spacexinfo.data.ext.getRocketDetailsList
import simply.homework.spacextimer.spacexinfo.domain.model.RocketDetails
import simply.homework.spacextimer.spacexinfo.presentation.composable.button.WikipediaButton
import simply.homework.spacextimer.spacexinfo.presentation.composable.actionbar.SpaceXActionBar
import simply.homework.spacextimer.spacexinfo.presentation.composable.items.RocketDetailsItem
import simply.homework.spacextimer.spacexinfo.presentation.composable.pager.ImagePagerWithIndicator
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract
import simply.homework.spacextimer.spacexinfo.presentation.loading.SpaceXLoading
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpaceXDetailsScreen(modifier: Modifier, viewModel: SpaceXInfoMVIViewModel) {
    val selectedItem = viewModel.viewState.value.selectedEvent.value

    if (viewModel.viewState.value.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            SpaceXLoading()
        }
    } else {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            LazyColumn(modifier = modifier.fillMaxSize()) {

                stickyHeader(key = "action_bar") {
                    //TopAppBar
                    SpaceXActionBar(
                        title = selectedItem?.name ?: "ERROR",
                        icon = Icons.AutoMirrored.Filled.ArrowBack
                    ) {
                        viewModel.setEvent(InfoContract.Event.ReturnBackFromDetailScreen)
                    }
                }

                item {
                    ScreenContent(
                        modifier = modifier, rocketDetails = selectedItem, viewModel = viewModel
                    )
                }
            }
        }
    }
}


@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier, rocketDetails: RocketDetails?, viewModel: SpaceXInfoMVIViewModel
) {
    val remainingTime = viewModel.viewState.value.selectedEventTimerValue
    if (rocketDetails != null) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                // image pager
                ImagePagerWithIndicator(
                    imageUrls = rocketDetails.flickrImages,
                )

                Text(
                    text = buildAnnotatedString {
                        appendItemNameAndDescription(
                            name = rocketDetails.name,
                            isShowingTimer = true,
                            description = remainingTime.countTime()
                        )
                    }, modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = rocketDetails.description, style = TextStyle(
                        fontSize = 13.sp, lineHeight = 21.sp
                    ), modifier = Modifier.padding(4.dp)
                )

                WikipediaButton(modifier = Modifier.padding(start = 4.dp)) {
                    viewModel.setEvent(InfoContract.Event.WikipediaButtonClick(rocketDetails))
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(0.dp, 1000.dp)
                ) {
                    itemsIndexed(rocketDetails.getRocketDetailsList()) { index, item ->
                        RocketDetailsItem(
                            descriptionTitle = item.first, description = item.second, index = index
                        )
                    }
                }
            }
        }

    } else {
        Text(
            text = "Something went wrong",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
    }
}