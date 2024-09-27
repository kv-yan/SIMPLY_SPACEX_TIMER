package simply.homework.spacextimer.spacexinfo.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import simply.homework.spacextimer.spacexinfo.presentation.comp.items.SpaceXRaceItem
import simply.homework.spacextimer.spacexinfo.presentation.comp.items.SpaceXRaceSelectedItemDetails
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

@androidx.compose.runtime.Composable
fun SpaceXInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: SpaceXInfoMVIViewModel,
) {
    val selectedItem = viewModel.viewState.value.domainInfoItems.firstOrNull()
    LazyColumn {
        item {
            if (selectedItem != null) {
                SpaceXRaceSelectedItemDetails(selectedItem = selectedItem)
            }
        }

        items(viewModel.viewState.value.domainInfoItems) {
            SpaceXRaceItem(item = it)
        }

        item {
            // for comfort scrolling
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}




