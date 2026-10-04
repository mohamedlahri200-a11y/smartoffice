package com.mohamed.smartoffice.domain.rule

import com.mohamed.smartoffice.domain.model.Booking

/**
 * Règles métier qui portent sur plusieurs réservations.
 * `object` : un singleton, car ces règles n'ont pas d'état.
 */
object BookingRules {

    /**
     * Renvoie la première réservation existante en conflit avec [candidate], ou `null`.
     * `firstOrNull` s'arrête dès le premier conflit trouvé : inutile de parcourir toute la liste.
     */
    fun findConflict(candidate: Booking, existing: Iterable<Booking>): Booking? =
        existing.firstOrNull { it.id != candidate.id && it.conflictsWith(candidate) }
}