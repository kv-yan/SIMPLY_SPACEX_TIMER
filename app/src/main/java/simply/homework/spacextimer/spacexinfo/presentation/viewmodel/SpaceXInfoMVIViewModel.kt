package simply.homework.spacextimer.spacexinfo.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import simply.homework.spacextimer.spacexinfo.data.helpers.CountdownTimerHelper
import simply.homework.spacextimer.spacexinfo.data.helpers.FlightDateHelper
import simply.homework.spacextimer.spacexinfo.domain.model.Rocket
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetRocketDetailsUseCase
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetRocketsUseCase
import simply.homework.spacextimer.spacexinfo.domain.usecase.OpenLinkUseCase
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract
import java.util.Calendar


class SpaceXInfoMVIViewModel(
    private val getRocketsUseCase: GetRocketsUseCase,
    private val getRocketDetailsUseCase: GetRocketDetailsUseCase,
    private val flightDateHelper: FlightDateHelper,
    private val countdownTimerHelper: CountdownTimerHelper,
    private val openLinkUseCase: OpenLinkUseCase

) : BaseViewModel<InfoContract.Event, InfoContract.State, InfoContract.Effect>() {

    private var countdownJob: Job? = null // Add a Job to control the timer

    init {
        viewModelScope.launch {
            val infos = getRocketsUseCase.invoke()
            val selectedRocket = viewState.value.selectedEvent.value

            val selectedInfoItem = selectedRocket.takeIf {
                it?.id == infos.firstOrNull()?.id
            }

            val upcomingFlightDate = selectedInfoItem?.firstFlight?.let {
                flightDateHelper.calculateUpcomingFlightDate(it, 0)
            }

            updateStateWithTimer(infos, upcomingFlightDate)
            startCountdownForSelectedItem() // Start the countdown on initialization
        }
    }

    override fun handleEvents(event: InfoContract.Event) {

        when (event) {
            is InfoContract.Event.InfoItemClick -> onItemClicked(event.rocket)
            is InfoContract.Event.InfoItemDetailsClick -> onItemDetailsClicked(event.rocket)
            is InfoContract.Event.WikipediaButtonClick -> {
                val url = viewState.value.selectedEvent.value?.wikipedia
                if (url != null) {
                    openLink(url)
                }
            }

            InfoContract.Event.StartSelectedItemCountdownTime -> {
                startCountdownForSelectedItem()
            }

            InfoContract.Event.ReturnBackFromDetailScreen -> {
                setEffect { InfoContract.Effect.ReturnBackFromDetailScreen }
            }

        }
    }

    private fun updateStateWithTimer(infos: List<Rocket>, upcomingFlightDate: Calendar?) {
        viewState.value.selectedEventTimerValue.value =
            upcomingFlightDate?.timeInMillis ?: 259200000L
        setState {
            viewState.value.copy(
                rockets = infos,
                isLoading = false,
            )
        }
    }

    private fun updateRemainingTime(remainingTime: Long) {
        viewState.value.selectedEventTimerValue.value = remainingTime
    }

    private fun startCountdownForSelectedItem() {
        countdownJob?.cancel() // Cancel the previous timer if it's running
        countdownJob = viewModelScope.launch {
            // Start the countdown from 3 days (259200000mls)
            countdownTimerHelper.startCountdownTimer(viewState.value.selectedEventTimerValue.value) { remaining ->
                updateRemainingTime(remaining)
            }
        }
    }

    private fun onItemClicked(infoItem: Rocket) {
        viewModelScope.launch {
            val selectedRocket = viewState.value.selectedEvent
            viewState.value.selectedEventTimerValue.value = 259200000L // 3 days in milliseconds
            val rocketDetails = getRocketDetailsUseCase.invoke(infoItem.id)
            setState {
                selectedRocket.value = rocketDetails
                viewState.value.copy(
                    selectedEventTimerValue = viewState.value.selectedEventTimerValue
                )
            }
            startCountdownForSelectedItem()
        }
    }

    private fun getRocketDetails(rocketId: String) {
        viewModelScope.launch {
            setState {
                viewState.value.copy(isLoading = true)
            }

            val rocketDetailsResult = getRocketDetailsUseCase.invoke(rocketId)

            val existingImages =
                viewState.value.rockets.find { it.id == rocketId }?.imageList.orEmpty()

            val updatedRocketDetails = rocketDetailsResult?.copy(flickrImages = existingImages)

            setState {
                val selectedRocket = viewState.value.selectedEvent
                selectedRocket.value = updatedRocketDetails
                viewState.value.copy(isLoading = false)
            }
        }
    }

    private fun onItemDetailsClicked(infoItem: Rocket) {
        viewModelScope.launch {
            val rocketDetails = getRocketDetailsUseCase.invoke(infoItem.id)
            val selectedRocket = viewState.value.selectedEvent
            selectedRocket.value = rocketDetails

            getRocketDetails(infoItem.id)

            setEffect { InfoContract.Effect.NavigateToDetails(rocketDetails) }
        }
    }

    override fun setInitialState() = InfoContract.State.INITIAL

    private fun openLink(url: String) {
        viewModelScope.launch {
            openLinkUseCase.invoke(url)
        }
    }
}

