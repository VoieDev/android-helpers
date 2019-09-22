package ajahncharles.android.helpers

import com.google.firebase.Timestamp
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

fun LocalDateTime.toTimestamp() = Timestamp(atZone(ZoneId.systemDefault()).toEpochSecond(), nano)

fun Timestamp.toLocalDateTime(zone: ZoneId = ZoneId.systemDefault()): LocalDateTime =
    LocalDateTime.ofInstant(
        org.threeten.bp.Instant.ofEpochMilli(seconds * 1000 + nanoseconds / 1000000), zone
    )
