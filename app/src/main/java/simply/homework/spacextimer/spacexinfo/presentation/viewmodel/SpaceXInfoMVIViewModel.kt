package simply.homework.spacextimer.spacexinfo.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import simply.homework.spacextimer.spacexinfo.data.helpers.CountdownTimerHelper
import simply.homework.spacextimer.spacexinfo.data.helpers.FlightDateHelper
import simply.homework.spacextimer.spacexinfo.domain.model.Rocket
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetRocketDetailsUseCase
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetRocketsUseCase
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract
import java.util.Calendar

private const val TAG = "SpaceXInfoMVIViewModel"

class SpaceXInfoMVIViewModel(
    private val getRocketsUseCase: GetRocketsUseCase,
    private val getRocketDetailsUseCase: GetRocketDetailsUseCase,
    private val flightDateHelper: FlightDateHelper,
    private val countdownTimerHelper: CountdownTimerHelper
) : BaseViewModel<InfoContract.Event, InfoContract.State, InfoContract.Effect>() {

    private var countdownJob: Job? = null // Add a Job to control the timer

    init {
        viewModelScope.launch {
            val infos = getRocketsUseCase.invoke()

            val selectedInfoItem = viewState.value.selectedEvent.takeIf {
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
            viewState.value.selectedEventTimerValue.value = 259200000L // 3 days in milliseconds
            val rocketDetails = getRocketDetailsUseCase.invoke(infoItem.id)
            setState {
                viewState.value.copy(
                    selectedEvent = rocketDetails,// and set here
                    selectedEventTimerValue = viewState.value.selectedEventTimerValue
                )
            }
            startCountdownForSelectedItem()
        }
    }

    fun getRocketDetails(rocketId: String) {
        viewModelScope.launch {
            // Set the loading state to true before fetching the details
            setState {
                viewState.value.copy(isLoading = true)
            }

            // Get the rocket details from the use case
            val rocketDetailsResult = getRocketDetailsUseCase.invoke(rocketId)

            // Find the existing image list from the rockets
            val existingImages = viewState.value.rockets.find { it.id == rocketId }?.imageList.orEmpty()

            // Create a new rocketDetails object, ensuring it's updated with the existing images
            val updatedRocketDetails = rocketDetailsResult?.copy(flickr_images = existingImages)

            // Update the state with the fetched rocket details and stop the loading
            setState {
                viewState.value.copy(selectedEvent = updatedRocketDetails, isLoading = false)
            }
        }
    }

    private fun onItemDetailsClicked(infoItem: Rocket) {
        viewModelScope.launch {
            val rocketDetails = getRocketDetailsUseCase.invoke(infoItem.id)

            setState { viewState.value.copy(selectedEvent = rocketDetails) }
            setEffect { InfoContract.Effect.NavigateToDetails(rocketDetails) }
        }
    }

    override fun setInitialState() = InfoContract.State.INITIAL
}

