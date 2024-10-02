package simply.homework.spacextimer.spacexinfo.presentation.screen.rockets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import simply.homework.spacextimer.spacexinfo.presentation.composable.items.SpaceXRaceSelectedItemDetails
import simply.homework.spacextimer.spacexinfo.presentation.composable.items.SpaceXRocketsItem
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract
import simply.homework.spacextimer.spacexinfo.presentation.loading.SpaceXLoading
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

@OptIn(ExperimentalFoundationApi::class)
@androidx.compose.runtime.Composable
fun SpaceXRocketsScreen(
    modifier: Modifier = Modifier,
    viewModel: SpaceXInfoMVIViewModel,
) {

    val selectedItem = viewModel.viewState.value.selectedEvent.value
    val remainingTimerValue = viewModel.viewState.value.selectedEventTimerValue
    val isLoading = viewModel.viewState.value.isLoading

    if (isLoading) {
        SpaceXLoading()
    } else {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            stickyHeader(key = "selected_item") {
                if (selectedItem != null) {
                    Surface(modifier = Modifier.fillMaxWidth()) {
                        SpaceXRaceSelectedItemDetails(
                            selectedItem = selectedItem,
                            remainingTimerValue = remainingTimerValue,
                            viewModel = viewModel
                        )
                    }
                }
            }


            items(viewModel.viewState.value.rockets, key = { it.id }) {
                SpaceXRocketsItem(item = it, onItemClick = { item ->
                    viewModel.setEvent(InfoContract.Event.InfoItemClick(item))
                }, onDetailsClick = { item ->
                    viewModel.setEvent(InfoContract.Event.InfoItemDetailsClick(item))
                })
            }

            item {
                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    }
}




