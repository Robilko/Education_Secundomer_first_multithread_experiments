package com.example.secundomer.util

/**Класс, который переводит миллисекунды в понятный формат.
 * Тут мы просто делим значение в миллисекундах на 1000 или на 60 без остатка. Если время на
секундомере меньше часа, то мы отображаем минуты, секунды и миллисекунды. Если больше —
отображаем часы, минуты и секунды. */
class TimestampMillisecondsFormatter {
    // Стандартный метод подходит для проверки тестов на Equals и NotEquals
    fun format(timestamp: Long): String {
        val millisecondsFormatted = (timestamp % 1000).pad(3)
        val seconds = timestamp / 1000
        val secondsFormatted = (seconds % 60).pad(2)
        val minutes = seconds / 60
        val minutesFormatted = (minutes % 60).pad(2)
        val hours = minutes / 60
        return if (hours > 0) {
            val hoursFormatted = (minutes / 60).pad(2)
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
        }
    }


    /**
     * Функция-расширение Long.pad, которую мы написали сами. Благодаря этому
    простому и изящному решению мы просто отображаем нужное
    количество нулей для каждого параметра, обращаясь напрямую к типу Long.

    padStart дополняет строку до указанной длины в начале указанным символом или пробелом.
    Параметры:
    length - желаемая длина строки.
    padChar - символ, которым дополняется строка, если ее длина меньше указанной длины.
     */
    private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')

    /** Новые методы для тестирования к заданию 3 в ДЗ урока 1*/
    // Метод для проверки на ArrayEquals
    fun format(hours: Long, minutes: Long, seconds: Long): Array<String> {
        return arrayOf("$hours", "$minutes", "$seconds")
    }

        // Метод для проверки на Null и на NotNull
    fun format(timeFormat: Long?): String? {
        return if (timeFormat == null) {
            null
        } else {
            format(timeFormat)
        }
    }
    // Метод для проверки на assertSame
    fun format(): String {
        return "00:00:000"
    }
}