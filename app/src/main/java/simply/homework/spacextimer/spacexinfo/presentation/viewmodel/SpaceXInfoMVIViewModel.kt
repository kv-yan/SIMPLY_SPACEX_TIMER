package simply.homework.spacextimer.spacexinfo.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import simply.homework.spacextimer.spacexinfo.data.helpers.CountdownTimerHelper
import simply.homework.spacextimer.spacexinfo.data.helpers.FlightDateHelper
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfoItem
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetInfoUseCase
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract
import java.util.Calendar

private const val TAG = "SpaceXInfoMVIViewModel"

class SpaceXInfoMVIViewModel(
    private val getInfoUseCase: GetInfoUseCase,
    private val flightDateHelper: FlightDateHelper,
    private val countdownTimerHelper: CountdownTimerHelper
) : BaseViewModel<InfoContract.Event, InfoContract.State, InfoContract.Effect>() {

    init {
        viewModelScope.launch {
            val infos = getInfoUseCase.invoke()

            val selectedInfoItem = viewState.value.selectedEvent.takeIf { infos.contains(it) }
            val upcomingFlightDate = selectedInfoItem?.first_flight?.let {
                flightDateHelper.calculateUpcomingFlightDate(it, 0)
            }

            updateStateWithTimer(infos, upcomingFlightDate)
            upcomingFlightDate?.let {
                countdownTimerHelper.startCountdownTimer(it.timeInMillis) { remainingTime ->
                    updateRemainingTime(remainingTime)
                }
            }
        }
    }

    private fun updateStateWithTimer(
        infos: List<DomainInfoItem>,
        upcomingFlightDate: Calendar?
    ) {
        setState {
            viewState.value.copy(
                domainInfoItems = infos,
                selectedEvent = infos.firstOrNull(),
                selectedEventTimerValue = upcomingFlightDate?.timeInMillis ?: 0L
            )
        }
    }

    private fun updateRemainingTime(remainingTime: Long) {
        setState {
            viewState.value.copy(selectedEventTimerValue = remainingTime)
        }
    }

    fun startCountdownTimerForSelectedItem() {
        viewModelScope.launch {
            val item = viewState.value.selectedEvent
            val upcomingFlightTime = item?.first_flight?.let {
                flightDateHelper.calculateUpcomingFlightDate(
                    it,
                    viewState.value.domainInfoItems.indexOf(item)
                ).timeInMillis
            }
            val remainingTime = upcomingFlightTime?.minus(System.currentTimeMillis())

            if (remainingTime != null) {
                if (remainingTime > 0) {
                    countdownTimerHelper.startCountdownTimer(remainingTime) { remaining ->
                        updateRemainingTime(remaining)
                    }
                }
            }
        }
    }

    override fun setInitialState() = InfoContract.State.INITIAL

    override fun handleEvents(event: InfoContract.Event) {
        when (event) {
            is InfoContract.Event.InfoItemClick -> onItemClicked(event.infoItem)
            is InfoContract.Event.InfoItemDetailsClick -> onItemDetailsClicked(event.infoItem)
            InfoContract.Event.StartSelectedItemCountdownTime -> {
                startCountdownTimerForSelectedItem()
                setEffect { InfoContract.Effect.StartSelectedItemCountdownTime }
            }
        }
    }

    private fun onItemClicked(infoItem: DomainInfoItem) {
        setState {
            viewState.value.copy(
                selectedEventTimerValue = 259200000, // 3 days in milliseconds
                selectedEvent = infoItem
            )
        }
    }

    private fun onItemDetailsClicked(infoItem: DomainInfoItem) {
        setState { viewState.value.copy(selectedEvent = infoItem) }
    }
}

