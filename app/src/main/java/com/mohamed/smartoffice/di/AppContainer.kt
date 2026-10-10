package com.mohamed.smartoffice.di

import com.mohamed.smartoffice.data.repository.FakeSpaceRepository
import com.mohamed.smartoffice.domain.repository.SpaceRepository

/**
 * Racine de composition (injection de dépendances manuelle).
 * C'est le SEUL endroit qui choisit les implémentations concrètes. Sera remplacé par Hilt.
 */
object AppContainer {
    val spaceRepository: SpaceRepository by lazy { FakeSpaceRepository() }
}