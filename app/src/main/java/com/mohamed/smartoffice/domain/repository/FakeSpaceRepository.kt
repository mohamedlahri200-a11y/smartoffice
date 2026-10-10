package com.mohamed.smartoffice.data.repository

import com.mohamed.smartoffice.data.fake.FakeSpaces
import com.mohamed.smartoffice.domain.model.Space
import com.mohamed.smartoffice.domain.repository.SpaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Implémentation temporaire du dépôt, alimentée par des données en mémoire.
 * Sera remplacée par une implémentation Room (étape 4) puis réseau (étape 7).
 */
class FakeSpaceRepository(
    initialSpaces: List<Space> = FakeSpaces.all,
) : SpaceRepository {

    private val spaces = MutableStateFlow(initialSpaces)

    override fun observeSpaces(): Flow<List<Space>> = spaces.asStateFlow()
}