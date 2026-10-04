package com.mohamed.smartoffice.domain

import com.mohamed.smartoffice.domain.model.Booking
import com.mohamed.smartoffice.domain.model.BookingId
import com.mohamed.smartoffice.domain.model.BookingStatus
import com.mohamed.smartoffice.domain.model.SpaceId
import com.mohamed.smartoffice.domain.model.TimeSlot
import com.mohamed.smartoffice.domain.model.UserId
import java.time.LocalDateTime

/** Jour fixe pour des tests reproductibles (jamais `now()` dans un test). */
private val DAY: LocalDateTime = LocalDateTime.of(2026, 10, 5, 0, 0)

fun slot(startHour: Int, endHour: Int): TimeSlot =
    TimeSlot(DAY.withHour(startHour), DAY.withHour(endHour))

fun booking(
    id: Long = 1,
    spaceId: Long = 1,
    startHour: Int = 9,
    endHour: Int = 11,
    status: BookingStatus = BookingStatus.CONFIRMED,
): Booking = Booking(
    id = BookingId(id),
    spaceId = SpaceId(spaceId),
    userId = UserId(1),
    slot = slot(startHour, endHour),
    status = status,
)