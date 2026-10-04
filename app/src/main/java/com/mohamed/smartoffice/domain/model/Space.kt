package com.mohamed.smartoffice.domain.model

enum class SpaceType { DESK, MEETING_ROOM, PHONE_BOOTH }

enum class Equipment { SCREEN, DOCKING_STATION, WEBCAM, WHITEBOARD, VIDEO_CONFERENCE }

/** Un espace réservable : bureau, salle de réunion, cabine téléphonique. */
data class Space(
    val id: SpaceId,
    val name: String,
    val type: SpaceType,
    val floor: Int,
    val capacity: Int,
    val equipments: Set<Equipment> = emptySet(),
) {
    init {
        require(name.isNotBlank()) { "Le nom de l'espace est obligatoire" }
        require(capacity > 0) { "La capacité doit être positive (reçu : $capacity)" }
    }

    /** Recherche en temps constant grâce au Set. */
    fun hasAll(required: Set<Equipment>): Boolean = equipments.containsAll(required)
}