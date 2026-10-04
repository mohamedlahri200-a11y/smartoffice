package com.mohamed.smartoffice.domain

import com.mohamed.smartoffice.domain.model.TimeSlot
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Duration
import java.time.LocalDateTime

class TimeSlotTest {

    @Test
    fun `creneaux croises - chevauchement`() {
        assertTrue(slot(9, 11).overlaps(slot(10, 12)))
    }

    @Test
    fun `creneau inclus dans un autre - chevauchement`() {
        assertTrue(slot(9, 12).overlaps(slot(10, 11)))
    }

    @Test
    fun `creneaux qui se suivent - pas de chevauchement`() {
        assertFalse(slot(9, 10).overlaps(slot(10, 11)))
    }

    @Test
    fun `le chevauchement est symetrique`() {
        val a = slot(9, 11)
        val b = slot(10, 12)
        assertEquals(a.overlaps(b), b.overlaps(a))
    }

    @Test
    fun `la duree est calculee`() {
        assertEquals(Duration.ofHours(2), slot(9, 11).duration)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `fin avant debut - creation refusee`() {
        slot(11, 9)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `creneau de duree nulle - creation refusee`() {
        val t = LocalDateTime.of(2026, 10, 5, 9, 0)
        TimeSlot(t, t)
    }
}