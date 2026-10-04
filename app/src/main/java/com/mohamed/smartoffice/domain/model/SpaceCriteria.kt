package com.mohamed.smartoffice.domain.model

/**
 * Critères de recherche d'un espace.
 * `null` pour le type signifie « tous les types » ; un ensemble vide signifie « aucun équipement exigé ».
 */
data class SpaceCriteria(
    val type: SpaceType? = null,
    val equipments: Set<Equipment> = emptySet(),
) {
    fun matches(space: Space): Boolean =
        (type == null || space.type == type) && space.hasAll(equipments)
}