package com.mohamed.smartoffice.ui.spaces

import androidx.annotation.StringRes
import com.mohamed.smartoffice.R
import com.mohamed.smartoffice.domain.model.Equipment
import com.mohamed.smartoffice.domain.model.SpaceType

/** Libellé affichable de chaque type d'espace (le domaine reste indépendant d'Android). */
@get:StringRes
val SpaceType.labelRes: Int
    get() = when (this) {
        SpaceType.DESK -> R.string.space_type_desk
        SpaceType.MEETING_ROOM -> R.string.space_type_meeting_room
        SpaceType.PHONE_BOOTH -> R.string.space_type_phone_booth
    }

/** Libellé affichable de chaque équipement. */
@get:StringRes
val Equipment.labelRes: Int
    get() = when (this) {
        Equipment.SCREEN -> R.string.equipment_screen
        Equipment.DOCKING_STATION -> R.string.equipment_docking_station
        Equipment.WEBCAM -> R.string.equipment_webcam
        Equipment.WHITEBOARD -> R.string.equipment_whiteboard
        Equipment.VIDEO_CONFERENCE -> R.string.equipment_video_conference
    }