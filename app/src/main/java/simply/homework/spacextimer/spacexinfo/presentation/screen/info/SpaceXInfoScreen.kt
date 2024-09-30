package simply.homework.spacextimer.spacexinfo.presentation.screen.info

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import simply.homework.spacextimer.spacexinfo.presentation.composable.items.SpaceXRaceItem
import simply.homework.spacextimer.spacexinfo.presentation.composable.items.SpaceXRaceSelectedItemDetails
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

@OptIn(ExperimentalFoundationApi::class)
@androidx.compose.runtime.Composable
fun SpaceXInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: SpaceXInfoMVIViewModel,
) {

    val selectedItem = viewModel.viewState.value.selectedEvent
    val remainingTimerValue = viewModel.viewState.value.selectedEventTimerValue

    LazyColumn(modifier = modifier.fillMaxSize()) {

        item(key = "selected_item") {
            if (selectedItem != null) {
                Surface(modifier = Modifier.animateItemPlacement()) {
                    SpaceXRaceSelectedItemDetails(
                        selectedItem = selectedItem,
                        remainingTimerValue = remainingTimerValue,
                        viewModel = viewModel
                    )

                }
            }
        }


        items(viewModel.viewState.value.domainInfoItems, key = { it.id }) {
            SpaceXRaceItem(
                item = it,
                onItemClick = { item ->
                    viewModel.setEvent(InfoContract.Event.InfoItemClick(item))
                },
                onDetailsClick = { item ->
                    viewModel.setEvent(InfoContract.Event.InfoItemDetailsClick(item))
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}




