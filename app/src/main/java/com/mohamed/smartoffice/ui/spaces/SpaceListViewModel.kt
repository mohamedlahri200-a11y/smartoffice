package com.mohamed.smartoffice.ui.spaces

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.smartoffice.domain.model.Equipment
import com.mohamed.smartoffice.domain.model.SpaceCriteria
import com.mohamed.smartoffice.domain.model.SpaceType
import com.mohamed.smartoffice.domain.repository.SpaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

/**
 * ViewModel de l'écran des espaces : il possède l'état, reçoit les événements
 * et expose un unique flux d'état ([uiState]) que l'écran se contente d'afficher.
 */
class SpaceListViewModel(
    repository: SpaceRepository,
) : ViewModel() {

    private val criteria = MutableStateFlow(SpaceCriteria())

    val uiState: StateFlow<SpaceListUiState> =
        combine(repository.observeSpaces(), criteria) { spaces, currentCriteria ->
            SpaceListUiState(
                spaces = spaces.filter(currentCriteria::matches),
                criteria = currentCriteria,
                isLoading = false,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = SpaceListUiState(),
        )

    fun onTypeSelected(type: SpaceType?) {
        criteria.update { it.copy(type = type) }
    }

    fun onEquipmentToggled(equipment: Equipment) {
        criteria.update { current ->
            val equipments = current.equipments
            current.copy(
                equipments = if (equipment in equipments) equipments - equipment else equipments + equipment,
            )
        }
    }

    private companion object {
        /** Garde le flux actif 5 s après le départ de l'écran : une rotation ne relance rien. */
        const val STOP_TIMEOUT_MILLIS = 5_000L
    }
}