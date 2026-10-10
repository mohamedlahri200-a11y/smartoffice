package com.mohamed.smartoffice.ui.spaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamed.smartoffice.R
import com.mohamed.smartoffice.data.fake.FakeSpaces
import com.mohamed.smartoffice.domain.model.Equipment
import com.mohamed.smartoffice.domain.model.Space
import com.mohamed.smartoffice.domain.model.SpaceCriteria
import com.mohamed.smartoffice.domain.model.SpaceType
import com.mohamed.smartoffice.ui.theme.SmartOfficeTheme

/** Composable SANS état : il affiche [uiState] et remonte les événements. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceListScreen(
    uiState: SpaceListUiState,
    onTypeSelected: (SpaceType?) -> Unit,
    onEquipmentToggled: (Equipment) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text(stringResource(R.string.spaces_title)) }) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            SpaceFilters(
                selectedType = uiState.criteria.type,
                requiredEquipments = uiState.criteria.equipments,
                onTypeSelected = onTypeSelected,
                onEquipmentToggled = onEquipmentToggled,
                modifier = Modifier.padding(vertical = 8.dp),
            )
            when {
                uiState.isLoading -> LoadingState()
                uiState.spaces.isEmpty() -> EmptyState()
                else -> SpaceList(spaces = uiState.spaces)
            }
        }
    }
}

@Composable
private fun SpaceList(spaces: List<Space>) {
    Text(
        text = stringResource(R.string.spaces_count, spaces.size),
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier.padding(horizontal = 16.dp),
    )
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(items = spaces, key = { it.id.value }) { space ->
            SpaceCard(space = space)
        }
    }
}

@Composable
private fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.spaces_empty),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SpaceListScreenPreview() {
    SmartOfficeTheme {
        SpaceListScreen(
            uiState = SpaceListUiState(spaces = FakeSpaces.all, isLoading = false),
            onTypeSelected = {},
            onEquipmentToggled = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SpaceListScreenEmptyPreview() {
    SmartOfficeTheme {
        SpaceListScreen(
            uiState = SpaceListUiState(
                criteria = SpaceCriteria(SpaceType.PHONE_BOOTH, setOf(Equipment.VIDEO_CONFERENCE)),
                isLoading = false,
            ),
            onTypeSelected = {},
            onEquipmentToggled = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SpaceListScreenLoadingPreview() {
    SmartOfficeTheme {
        SpaceListScreen(uiState = SpaceListUiState(), onTypeSelected = {}, onEquipmentToggled = {})
    }
}