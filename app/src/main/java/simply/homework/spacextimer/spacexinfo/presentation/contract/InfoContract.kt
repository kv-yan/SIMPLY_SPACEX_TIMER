package simply.homework.spacextimer.spacexinfo.presentation.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfoItem
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.ViewEffect
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.ViewEvent
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.ViewState

class InfoContract {
    sealed class Event : ViewEvent {
        data class InfoItemClick(val infoItem: DomainInfoItem) : Event()
        data class InfoItemDetailsClick(val infoItem: DomainInfoItem) : Event()
        data object ReturnBackFromDetailScreen : Event()
        data object StartSelectedItemCountdownTime : Event()
    }

    data class State(
        val domainInfoItems: List<DomainInfoItem>,
        val isLoading: Boolean = false,
        val selectedEventTimerValue: MutableState<Long>,
        val selectedEvent: DomainInfoItem? = domainInfoItems.firstOrNull()
    ) : ViewState {
        companion object {
            val INITIAL = State(
                domainInfoItems = emptyList(),
                selectedEventTimerValue = mutableLongStateOf(0L),
                selectedEvent = null
            )
        }
    }

    sealed class Effect : ViewEffect {
        data class NavigateToDetails(val infoItem: DomainInfoItem) : Effect()
        data object ReturnBackFromDetailScreen : Effect()
    }
}