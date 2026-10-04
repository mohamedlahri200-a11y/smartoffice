

package com.mohamed.smartoffice.domain.model

import java.time.Duration
import java.time.LocalDateTime

/**
 * Créneau horaire semi-ouvert [start, end) : `start` est inclus, `end` est exclu.
 * Ainsi, 9h–10h et 10h–11h ne se chevauchent pas.
 */
data class TimeSlot(
    val start: LocalDateTime,
    val end: LocalDateTime,
) {
    init {
        require(end > start) { "La fin du créneau ($end) doit être après le début ($start)" }
    }

    /** Calculée une seule fois, à la création (le créneau est immuable). */
    val duration: Duration = Duration.between(start, end)

    /** Deux créneaux se chevauchent si chacun commence avant la fin de l'autre. */
    fun overlaps(other: TimeSlot): Boolean = start < other.end && other.start < end
}