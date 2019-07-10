package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.extensions.Plurals.*
import ru.skillbranch.devintensive.extensions.TimeUnits.*

import java.lang.IllegalStateException
import kotlin.math.absoluteValue
import java.text.SimpleDateFormat
import java.util.*

enum class TimeUnits(val size: Long) {
    SECOND(1000L),
    MINUTE(60 * SECOND.size),
    HOUR(60 * MINUTE.size),
    DAY(24 * HOUR.size);

    fun plural(value: Long) = "$value ${pluralStrings[value.asPlurals]}"
}

enum class Plurals {
    ONE,
    FEW,
    MANY
}

val TimeUnits.pluralStrings
    get() = when (this) {
        SECOND -> mapOf(ONE to "секунду", FEW to "секунды", MANY to "секунд")
        MINUTE -> mapOf(ONE to "минуту", FEW to "минуты", MANY to "минут")
        HOUR -> mapOf(ONE to "час", FEW to "часа", MANY to "часов")
        DAY -> mapOf(ONE to "день", FEW to "дня", MANY to "дней")
    }

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    time += value * units.size
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String? {
    var time = this.time;
    val newTime: Long = time.minus(date.time);
    val diff = ((date.time + 200) / 1000 - (time + 200) / 1000) * 1000

    var message: String? = when (diff) {
        in 0..1 -> "только что"
        in 1000..45000 -> "несколько секунд назад"
        in 45000..75000 -> "минуту назад"
        in 75000..2700000 -> "${TimeUnits.MINUTE.plural(diff.absoluteValue / MINUTE.size)} назад"
        in 2700000..4500000 -> "час назад"
        in 4500000..79200000 -> "${TimeUnits.HOUR.plural(diff.absoluteValue / HOUR.size)} назад"
        in 79200000..93600000 -> "день назад"
        in 93600000..31104000000 -> "${TimeUnits.DAY.plural(diff.absoluteValue / DAY.size)} назад"

        in -1..0 -> "только что"
        in -45000..-1000 -> "через несколько секунд"
        in -75000..-45000 -> "через минуту"
        in -2700000..-75000 -> "через ${TimeUnits.MINUTE.plural(diff.absoluteValue / MINUTE.size)}"
        in -4500000..-2700000 -> "через час "
        in -79200000..-4500000 -> "через ${TimeUnits.HOUR.plural(diff.absoluteValue / HOUR.size)}"
        in -93600000..-79200000 -> "через день"
        in -31104000000..-93600000 -> "через ${TimeUnits.DAY.plural(diff.absoluteValue / DAY.size)}"
        else -> "более года назад"
    }

    return message;
}




val Long.asPlurals
    get() = when {
        this % 100L in 5L..20L -> MANY
        this % 10L == 1L -> ONE
        this % 10L in 2L..4L -> FEW
        else -> MANY
    }