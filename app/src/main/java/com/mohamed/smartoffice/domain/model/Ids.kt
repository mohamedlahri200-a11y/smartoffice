

package com.mohamed.smartoffice.domain.model

/**
 * Identifiants typés.
 * Un SpaceId ne peut pas être confondu avec un UserId : le compilateur l'interdit.
 */
@JvmInline
value class SpaceId(val value: Long)

@JvmInline
value class UserId(val value: Long)

@JvmInline
value class BookingId(val value: Long)