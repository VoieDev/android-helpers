package ajahncharles.android.helpers

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.content.Context
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/***
 * LocalDateTime month is 1-based; DatePicker month is 0-based
 */
suspend fun Context.pickDate(init: LocalDate): LocalDate =
    suspendCoroutine { cont ->
        DatePickerDialog(
            this,
            OnDateSetListener { datePicker, year, month, day ->
                // Pass dialog result to continuation
                cont.resume(LocalDate.of(year, month + 1, day))
            },
            init.year, init.monthValue - 1, init.dayOfMonth
        ).show()
    }

suspend fun Context.pickTime(init: LocalTime, is24Hour: Boolean = true): LocalTime =
    suspendCoroutine { cont ->
        TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                // Pass dialog result to continuation
                cont.resume(LocalTime.of(hour, minute))
            },
            init.hour, init.minute, is24Hour
        ).show()
    }

suspend fun Context.pickDate(init: LocalDateTime): LocalDateTime =
    init.with(pickDate(init.toLocalDate()))

suspend fun Context.pickTime(init: LocalDateTime, is24Hour: Boolean = true): LocalDateTime =
    init.with(pickTime(init.toLocalTime(), is24Hour))

suspend fun Context.pickDateAndTime(init: LocalDateTime, is24Hour: Boolean = true) =
    pickTime(pickDate(init))