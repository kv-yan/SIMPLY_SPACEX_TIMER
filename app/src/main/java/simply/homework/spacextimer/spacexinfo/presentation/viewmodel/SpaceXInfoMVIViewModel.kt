package simply.homework.spacextimer.spacexinfo.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
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

    private var countdownJob: Job? = null // Add a Job to control the timer

    init {
        viewModelScope.launch {
            val infos = getInfoUseCase.invoke()

            val selectedInfoItem = viewState.value.selectedEvent.takeIf { infos.contains(it) }
            val upcomingFlightDate = selectedInfoItem?.first_flight?.let {
                flightDateHelper.calculateUpcomingFlightDate(it, 0)
            }

            updateStateWithTimer(infos, upcomingFlightDate)

            startCountdownForSelectedItem() // Start the countdown on initialization
        }
    }

    override fun handleEvents(event: InfoContract.Event) {
        when (event) {
            is InfoContract.Event.InfoItemClick -> onItemClicked(event.infoItem)
            is InfoContract.Event.InfoItemDetailsClick -> onItemDetailsClicked(event.infoItem)
            InfoContract.Event.StartSelectedItemCountdownTime -> {
                startCountdownForSelectedItem() // Start the timer for the selected item
            }
        }
    }

    private fun updateStateWithTimer(
        infos: List<DomainInfoItem>, upcomingFlightDate: Calendar?
    ) {
        viewState.value.selectedEventTimerValue.value = upcomingFlightDate?.timeInMillis ?: 259200000L
        setState {
//
            viewState.value.copy(
                domainInfoItems = infos,
                selectedEvent = infos.firstOrNull(), // 3 days
            )
        }
    }

    private fun updateRemainingTime(remainingTime: Long) {
        viewState.value.selectedEventTimerValue.value = remainingTime
    }

    private fun startCountdownForSelectedItem() {
        countdownJob?.cancel() // Cancel the previous timer if it's running
        countdownJob = viewModelScope.launch {
            // Start the countdown from 3 days (259200000 ms)
            countdownTimerHelper.startCountdownTimer(viewState.value.selectedEventTimerValue.value) { remaining ->
                updateRemainingTime(remaining)
            }
        }
    }

    private fun onItemClicked(infoItem: DomainInfoItem) {
        viewState.value.selectedEventTimerValue.value = 259200000L // 3 days in milliseconds
        setState {
            viewState.value.copy(
                selectedEvent = infoItem,
                selectedEventTimerValue = viewState.value.selectedEventTimerValue
            )
        }
        startCountdownForSelectedItem() // Start the timer for the new selected item
    }

    private fun onItemDetailsClicked(infoItem: DomainInfoItem) {
        setState { viewState.value.copy(selectedEvent = infoItem) }
    }

    override fun setInitialState() = InfoContract.State.INITIAL
}

