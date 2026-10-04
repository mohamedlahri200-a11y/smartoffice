package com.mohamed.smartoffice.domain.model

import java.time.Duration

/** Cycle de vie d'une réservation. `blocksSlot` : le créneau est-il encore occupé ? */
enum class BookingStatus(val blocksSlot: Boolean) {
    CONFIRMED(blocksSlot = true),
    CHECKED_IN(blocksSlot = true),
    CANCELLED(blocksSlot = false),
    EXPIRED(blocksSlot = false),
}

/** Une réservation, immuable : chaque changement d'état produit une nouvelle copie. */
data class Booking(
    val id: BookingId,
    val spaceId: SpaceId,
    val userId: UserId,
    val slot: TimeSlot,
    val status: BookingStatus = BookingStatus.CONFIRMED,
) {
    init {
        require(slot.duration <= MAX_DURATION) {
            "Une réservation ne peut pas dépasser ${MAX_DURATION.toHours()} h"
        }
    }

    val isActive: Boolean get() = status.blocksSlot

    /** Conflit = même espace + deux réservations actives + créneaux qui se chevauchent. */
    fun conflictsWith(other: Booking): Boolean =
        spaceId == other.spaceId &&
                isActive && other.isActive &&
                slot.overlaps(other.slot)

    /** Annuler n'a de sens que pour une réservation active. */
    fun cancel(): Booking {
        check(isActive) { "Impossible d'annuler une réservation au statut $status" }
        return copy(status = BookingStatus.CANCELLED)
    }

    /** Le check-in n'est possible que pour une réservation confirmée. */
    fun checkIn(): Booking {
        check(status == BookingStatus.CONFIRMED) { "Check-in impossible au statut $status" }
        return copy(status = BookingStatus.CHECKED_IN)
    }

    companion object {
        /** Partagée par toutes les réservations (une seule instance en mémoire). */
        val MAX_DURATION: Duration = Duration.ofHours(10)
    }
}