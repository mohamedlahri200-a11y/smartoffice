package com.mohamed.smartoffice.domain

import com.mohamed.smartoffice.domain.model.BookingStatus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BookingTest {

    @Test
    fun `meme espace et creneaux croises - conflit`() {
        assertTrue(booking(spaceId = 1, startHour = 9, endHour = 11)
            .conflictsWith(booking(spaceId = 1, startHour = 10, endHour = 12)))
    }

    @Test
    fun `espaces differents - pas de conflit`() {
        assertFalse(booking(spaceId = 1).conflictsWith(booking(spaceId = 2)))
    }

    @Test
    fun `reservation annulee - ne bloque plus le creneau`() {
        val annulee = booking(status = BookingStatus.CANCELLED)
        assertFalse(annulee.conflictsWith(booking()))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `plus de 10 heures - creation refusee`() {
        booking(startHour = 8, endHour = 19)
    }

    @Test
    fun `annuler produit une copie annulee sans modifier l'original`() {
        val original = booking()
        val annulee = original.cancel()
        assertEquals(BookingStatus.CANCELLED, annulee.status)
        assertEquals(BookingStatus.CONFIRMED, original.status)
    }

    @Test(expected = IllegalStateException::class)
    fun `annuler deux fois - refuse`() {
        booking().cancel().cancel()
    }

    @Test
    fun `check-in d'une reservation confirmee`() {
        assertEquals(BookingStatus.CHECKED_IN, booking().checkIn().status)
    }

    @Test(expected = IllegalStateException::class)
    fun `check-in d'une reservation annulee - refuse`() {
        booking(status = BookingStatus.CANCELLED).checkIn()
    }
}