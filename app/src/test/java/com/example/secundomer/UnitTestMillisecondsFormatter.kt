package com.example.secundomer

import com.example.secundomer.util.TimestampMillisecondsFormatter
import org.junit.Test

import org.junit.Assert.*

/** Выполнение 2 пункта ДЗ к уроку №1 */
class UnitTestMillisecondsFormatter {
    @Test
    fun millisecondsFormatter_CorrectOutputMillisecondsTime_ReturnsTrue() {
        assertEquals(TimestampMillisecondsFormatter().format(123), "00:00:123")
    }

    @Test
    fun millisecondsFormatter_CorrectOutputSecondsTime_ReturnsTrue() {
        assertEquals(TimestampMillisecondsFormatter().format(12300), "00:12:300")
    }

    @Test
    fun millisecondsFormatter_CorrectOutputMinutesTime_ReturnsTrue() {
        assertEquals(TimestampMillisecondsFormatter().format(75000), "01:15:000")
    }

    @Test
    fun millisecondsFormatter_CorrectOutputHoursTime_ReturnsTrue() {
        assertEquals(TimestampMillisecondsFormatter().format(49750000), "13:49:10")
    }

    /** Выполнение 3 пункта ДЗ к уроку №1 */
    // a. Проверка на Equals
    @Test
    fun millisecondsFormatter_AssertEquals_ReturnsTrue() {
        assertEquals(TimestampMillisecondsFormatter().format(10), "00:00:010")
    }

    // b. Проверка на NotEquals
    @Test
    fun millisecondsFormatter_AssertNotEquals_ReturnsTrue() {
        assertNotEquals(TimestampMillisecondsFormatter().format(40), "00:00:050")
    }
    // c. Проверка на ArrayEquals
    @Test
    fun millisecondsFormatter_AssertArraysEquals_ReturnsTrue() {
        assertArrayEquals(TimestampMillisecondsFormatter().format(22, 31, 57), arrayOf("22", "31", "57"))
    }

    // d. Проверка на Null
    @Test
    fun millisecondsFormatter_AssertNull_ReturnsTrue() {
        assertNull(TimestampMillisecondsFormatter().format(null))
    }

    // e. Проверка на NotNull
    @Test
    fun millisecondsFormatter_AssertNotNull_ReturnsTrue() {
        assertNotNull(TimestampMillisecondsFormatter().format(34))
    }

    // f. Проверка на AssertSame
    @Test
    fun millisecondsFormatter_AssertSame_ReturnsTrue() {
        assertSame(TimestampMillisecondsFormatter().format(), "00:00:000")
    }
}