package simply.homework.spacextimer.spacexinfo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfoItem
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetInfoUseCase
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract

private const val TAG = "SpaceXInfoMVIViewModel"

class SpaceXInfoMVIViewModel(
    private val getInfoUseCase: GetInfoUseCase
) : BaseViewModel<InfoContract.Event, InfoContract.State, InfoContract.Effect>() {

    init {
        viewModelScope.launch {
            val infos = getInfoUseCase.invoke()
            setState { viewState.value.copy(domainInfoItems = infos) }
            Log.e(TAG, "init :$infos")
        }
    }

    override fun setInitialState() = InfoContract.State.INITIAL

    override fun handleEvents(event: InfoContract.Event) {
        when (event) {
            is InfoContract.Event.InfoItemClick -> {
                onItemClicked(viewState.value.domainInfoItems.first())
            }

            is InfoContract.Event.InfoItemDetailsClick -> {
                onItemDetailsClicked(viewState.value.domainInfoItems.first())
            }
        }
    }

    private fun onItemClicked(infoItem: DomainInfoItem) {}

    private fun onItemDetailsClicked(infoItem: DomainInfoItem) {}
}