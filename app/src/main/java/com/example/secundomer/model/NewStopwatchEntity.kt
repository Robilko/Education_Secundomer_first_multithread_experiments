package com.example.secundomer.model

import com.example.secundomer.model.calculate.ElapsedTimeCalculator
import com.example.secundomer.model.calculate.StopwatchStateCalculator
import com.example.secundomer.model.time.StopwatchStateHolder
import com.example.secundomer.model.time.TimestampProvider
import com.example.secundomer.util.TimestampMillisecondsFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class NewStopwatchEntity {

    private val timestampProvider by lazy {
        object : TimestampProvider {
            override fun getMilliseconds(): Long {
                return System.currentTimeMillis()
            }
        }
    }

    fun getNewStopwatch(): StopwatchModel = StopwatchModelImpl(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(Dispatchers.Main + SupervisorJob())
    )
}