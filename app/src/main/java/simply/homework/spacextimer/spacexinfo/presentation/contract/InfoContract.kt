package simply.homework.spacextimer.spacexinfo.presentation.contract

import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfoItem
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.ViewEffect
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.ViewEvent
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.ViewState

class InfoContract {
    sealed class Event : ViewEvent {
        data class InfoItemClick(val infoItem: DomainInfoItem) : Event()
        data class InfoItemDetailsClick(val infoItem: DomainInfoItem) : Event()
    }

    data class State(
        val domainInfoItems: List<DomainInfoItem>,
        val selectedEventTimerValue: Long,
    ) : ViewState {
        companion object {
            val INITIAL = State(domainInfoItems = emptyList(), selectedEventTimerValue = 0L)
        }
    }

    sealed class Effect : ViewEffect {
        // don't need implementation for now
    }
}