package simply.homework.spacextimer.spacexinfo.presentation.screen.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

@Composable
fun SpaceXDetailsScreen(modifier: Modifier, viewModel: SpaceXInfoMVIViewModel) {
    Text(
        text = viewModel.viewState.value.selectedEvent?.name.toString(),
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 24.sp
    )
}
