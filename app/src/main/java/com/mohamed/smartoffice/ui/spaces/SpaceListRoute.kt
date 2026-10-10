package com.mohamed.smartoffice.ui.spaces

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamed.smartoffice.di.AppContainer

/**
 * Point d'entrée de l'écran : relie le ViewModel (état + logique) à l'écran sans état.
 */
@Composable
fun SpaceListRoute(
    modifier: Modifier = Modifier,
    viewModel: SpaceListViewModel = viewModel { SpaceListViewModel(AppContainer.spaceRepository) },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SpaceListScreen(
        uiState = uiState,
        onTypeSelected = viewModel::onTypeSelected,
        onEquipmentToggled = viewModel::onEquipmentToggled,
        modifier = modifier,
    )
}