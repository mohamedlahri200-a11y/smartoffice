package com.mohamed.smartoffice.data.fake

import com.mohamed.smartoffice.domain.model.Equipment
import com.mohamed.smartoffice.domain.model.Space
import com.mohamed.smartoffice.domain.model.SpaceId
import com.mohamed.smartoffice.domain.model.SpaceType

/** Données de démonstration, en attendant la base locale (étape 4) et l'API (étape 7). */
object FakeSpaces {
    val all: List<Space> = listOf(
        Space(SpaceId(1), "Bureau 2.01", SpaceType.DESK, floor = 2, capacity = 1,
            equipments = setOf(Equipment.SCREEN, Equipment.DOCKING_STATION)),
        Space(SpaceId(2), "Bureau 2.02", SpaceType.DESK, floor = 2, capacity = 1,
            equipments = setOf(Equipment.SCREEN)),
        Space(SpaceId(3), "Bureau 3.10", SpaceType.DESK, floor = 3, capacity = 1),
        Space(SpaceId(4), "Salle Atlas", SpaceType.MEETING_ROOM, floor = 3, capacity = 8,
            equipments = setOf(Equipment.SCREEN, Equipment.VIDEO_CONFERENCE, Equipment.WHITEBOARD)),
        Space(SpaceId(5), "Salle Rif", SpaceType.MEETING_ROOM, floor = 1, capacity = 4,
            equipments = setOf(Equipment.WHITEBOARD)),
        Space(SpaceId(6), "Salle Sahara", SpaceType.MEETING_ROOM, floor = 4, capacity = 12,
            equipments = setOf(Equipment.SCREEN, Equipment.VIDEO_CONFERENCE, Equipment.WEBCAM)),
        Space(SpaceId(7), "Cabine A", SpaceType.PHONE_BOOTH, floor = 2, capacity = 1,
            equipments = setOf(Equipment.WEBCAM)),
        Space(SpaceId(8), "Cabine B", SpaceType.PHONE_BOOTH, floor = 3, capacity = 1),
    )
}