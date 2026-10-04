package com.mohamed.smartoffice.domain

import com.mohamed.smartoffice.domain.model.Equipment
import com.mohamed.smartoffice.domain.model.Space
import com.mohamed.smartoffice.domain.model.SpaceCriteria
import com.mohamed.smartoffice.domain.model.SpaceId
import com.mohamed.smartoffice.domain.model.SpaceType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SpaceCriteriaTest {

    private val desk = Space(
        SpaceId(1), "Bureau 2.01", SpaceType.DESK, floor = 2, capacity = 1,
        equipments = setOf(Equipment.SCREEN, Equipment.DOCKING_STATION),
    )
    private val room = Space(
        SpaceId(2), "Salle Atlas", SpaceType.MEETING_ROOM, floor = 3, capacity = 8,
        equipments = setOf(Equipment.SCREEN, Equipment.VIDEO_CONFERENCE),
    )

    @Test
    fun `criteres vides - tout correspond`() {
        val criteria = SpaceCriteria()
        assertTrue(criteria.matches(desk))
        assertTrue(criteria.matches(room))
    }

    @Test
    fun `filtre par type`() {
        val criteria = SpaceCriteria(type = SpaceType.MEETING_ROOM)
        assertEquals(listOf(room), listOf(desk, room).filter(criteria::matches))
    }

    @Test
    fun `filtre par equipement`() {
        val criteria = SpaceCriteria(equipments = setOf(Equipment.DOCKING_STATION))
        assertTrue(criteria.matches(desk))
        assertFalse(criteria.matches(room))
    }

    @Test
    fun `type et equipement combines`() {
        val criteria = SpaceCriteria(type = SpaceType.DESK, equipments = setOf(Equipment.VIDEO_CONFERENCE))
        assertFalse(criteria.matches(desk))
        assertFalse(criteria.matches(room))
    }
}