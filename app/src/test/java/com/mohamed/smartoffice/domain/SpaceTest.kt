package com.mohamed.smartoffice.domain

import com.mohamed.smartoffice.domain.model.Equipment
import com.mohamed.smartoffice.domain.model.Space
import com.mohamed.smartoffice.domain.model.SpaceId
import com.mohamed.smartoffice.domain.model.SpaceType
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SpaceTest {

    private fun desk(vararg equipments: Equipment) = Space(
        id = SpaceId(1),
        name = "Bureau 3.12",
        type = SpaceType.DESK,
        floor = 3,
        capacity = 1,
        equipments = equipments.toSet(),
    )

    @Test
    fun `possede tous les equipements demandes`() {
        val space = desk(Equipment.SCREEN, Equipment.DOCKING_STATION)
        assertTrue(space.hasAll(setOf(Equipment.SCREEN)))
    }

    @Test
    fun `equipement manquant`() {
        assertFalse(desk(Equipment.SCREEN).hasAll(setOf(Equipment.WEBCAM)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `nom vide - creation refusee`() {
        Space(SpaceId(1), "  ", SpaceType.DESK, floor = 0, capacity = 1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `capacite nulle - creation refusee`() {
        Space(SpaceId(1), "Salle A", SpaceType.MEETING_ROOM, floor = 0, capacity = 0)
    }
}