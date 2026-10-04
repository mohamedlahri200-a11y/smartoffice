package com.mohamed.smartoffice.ui.spaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamed.smartoffice.R
import com.mohamed.smartoffice.data.fake.FakeSpaces
import com.mohamed.smartoffice.domain.model.Space
import com.mohamed.smartoffice.ui.theme.SmartOfficeTheme

/** Carte d'un espace : nom, type, étage, capacité et équipements. */
@Composable
fun SpaceCard(
    space: Space,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = space.name,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = stringResource(space.type.labelRes),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = stringResource(R.string.space_floor_capacity, space.floor, space.capacity),
                style = MaterialTheme.typography.bodyMedium,
            )
            if (space.equipments.isNotEmpty()) {
                val labels = space.equipments
                    .sortedBy { it.ordinal }
                    .map { stringResource(it.labelRes) }
                Text(
                    text = labels.joinToString(separator = " · "),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpaceCardPreview() {
    SmartOfficeTheme {
        SpaceCard(space = FakeSpaces.all[3])
    }
}