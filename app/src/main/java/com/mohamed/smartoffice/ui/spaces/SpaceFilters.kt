package com.mohamed.smartoffice.ui.spaces

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mohamed.smartoffice.R
import com.mohamed.smartoffice.domain.model.Equipment
import com.mohamed.smartoffice.domain.model.SpaceType

/** Filtres de la liste : un type (ou tous) et des équipements exigés. Composable sans état. */
@Composable
fun SpaceFilters(
    selectedType: SpaceType?,
    requiredEquipments: Set<Equipment>,
    onTypeSelected: (SpaceType?) -> Unit,
    onEquipmentToggled: (Equipment) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        ChipRow {
            FilterChip(
                selected = selectedType == null,
                onClick = { onTypeSelected(null) },
                label = { Text(stringResource(R.string.filter_all)) },
            )
            SpaceType.entries.forEach { type ->
                FilterChip(
                    selected = selectedType == type,
                    onClick = { onTypeSelected(type) },
                    label = { Text(stringResource(type.labelRes)) },
                )
            }
        }
        ChipRow {
            Equipment.entries.forEach { equipment ->
                FilterChip(
                    selected = equipment in requiredEquipments,
                    onClick = { onEquipmentToggled(equipment) },
                    label = { Text(stringResource(equipment.labelRes)) },
                )
            }
        }
    }
}

/** Ligne de puces qui défile horizontalement si elle dépasse la largeur de l'écran. */
@Composable
private fun ChipRow(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        content()
    }
}