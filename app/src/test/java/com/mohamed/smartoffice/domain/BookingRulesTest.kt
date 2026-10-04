package com.mohamed.smartoffice.domain

import com.mohamed.smartoffice.domain.model.BookingStatus
import com.mohamed.smartoffice.domain.rule.BookingRules
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BookingRulesTest {

    private val existing = listOf(
        booking(id = 10, spaceId = 1, startHour = 9, endHour = 10),
        booking(id = 11, spaceId = 1, startHour = 14, endHour = 16),
        booking(id = 12, spaceId = 2, startHour = 9, endHour = 18),
        booking(id = 13, spaceId = 1, startHour = 11, endHour = 12, status = BookingStatus.CANCELLED),
    )

    @Test
    fun `creneau libre - aucun conflit`() {
        assertNull(BookingRules.findConflict(booking(id = 99, spaceId = 1, startHour = 10, endHour = 14), existing))
    }

    @Test
    fun `creneau occupe - renvoie la reservation en conflit`() {
        val conflict = BookingRules.findConflict(booking(id = 99, spaceId = 1, startHour = 15, endHour = 17), existing)
        assertEquals(11L, conflict?.id?.value)
    }

    @Test
    fun `une reservation ne entre pas en conflit avec elle-meme`() {
        assertNull(BookingRules.findConflict(existing[1], existing))
    }
}