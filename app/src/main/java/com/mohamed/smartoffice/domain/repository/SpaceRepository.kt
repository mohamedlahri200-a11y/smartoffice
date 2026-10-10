package com.mohamed.smartoffice.domain.repository

import com.mohamed.smartoffice.domain.model.Space
import kotlinx.coroutines.flow.Flow

/**
 * Contrat d'accès aux espaces.
 * Le domaine exprime le BESOIN ; la couche data fournit l'IMPLÉMENTATION.
 */
interface SpaceRepository {

    /** Flux qui émet la liste à jour des espaces, puis une nouvelle liste à chaque changement. */
    fun observeSpaces(): Flow<List<Space>>
}