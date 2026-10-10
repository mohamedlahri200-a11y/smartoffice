package com.mohamed.smartoffice.ui.spaces

import androidx.compose.runtime.Immutable
import com.mohamed.smartoffice.domain.model.Space
import com.mohamed.smartoffice.domain.model.SpaceCriteria

/** Tout ce dont l'écran a besoin pour s'afficher, en un seul objet immuable. */
@Immutable
data class SpaceListUiState(
    val spaces: List<Space> = emptyList(),
    val criteria: SpaceCriteria = SpaceCriteria(),
    val isLoading: Boolean = true,
)