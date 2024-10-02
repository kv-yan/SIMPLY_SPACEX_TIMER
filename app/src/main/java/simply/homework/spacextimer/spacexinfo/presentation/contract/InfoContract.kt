package simply.homework.spacextimer.spacexinfo.presentation.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import simply.homework.spacextimer.spacexinfo.domain.model.Rocket
import simply.homework.spacextimer.spacexinfo.domain.model.RocketDetails
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.ViewEffect
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.ViewEvent
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.ViewState

class InfoContract {
    sealed class Event : ViewEvent {
        data class InfoItemClick(val rocket: Rocket) : Event()
        data class InfoItemDetailsClick(val rocket: Rocket) : Event()
        data class WikipediaButtonClick(val rocket: RocketDetails) : Event()
        data object ReturnBackFromDetailScreen : Event()
        data object StartSelectedItemCountdownTime : Event()
    }

    data class State(
        val rockets: List<Rocket>,
        val isLoading: Boolean = true,
        val selectedEventTimerValue: MutableState<Long>,
        val selectedEvent: MutableState<RocketDetails?> = mutableStateOf(null)
    ) : ViewState {
        companion object {
            val INITIAL = State(
                rockets = emptyList(),
                isLoading = true,
                selectedEventTimerValue = mutableLongStateOf(0L),
                selectedEvent = mutableStateOf(null)
            )
        }
    }

    sealed class Effect : ViewEffect {
        data class NavigateToDetails(val infoItem: RocketDetails?) : Effect()
        data object ReturnBackFromDetailScreen : Effect()
    }
}