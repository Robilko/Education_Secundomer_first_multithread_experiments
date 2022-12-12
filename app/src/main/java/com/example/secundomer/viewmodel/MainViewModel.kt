package com.example.secundomer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.secundomer.model.NewStopwatchEntity
import com.example.secundomer.model.StopwatchModel
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val stopwatchModelFirst: StopwatchModel = NewStopwatchEntity().getNewStopwatch(),
    private val stopwatchModelSecond: StopwatchModel = NewStopwatchEntity().getNewStopwatch()
) : ViewModel() {

    val tickerFirst: StateFlow<String> = stopwatchModelFirst.ticker
    val tickerSecond: StateFlow<String> = stopwatchModelSecond.ticker

    fun onStartClickedFirst() {
        stopwatchModelFirst.start()
    }

    fun onPauseClickedFirst() {
        stopwatchModelFirst.pause()
    }

    fun onStopClickedFirst() {
        stopwatchModelFirst.stop()
    }

    fun onStartClickedSecond() {
        stopwatchModelSecond.start()
    }

    fun onPauseClickedSecond() {
        stopwatchModelSecond.pause()
    }
    fun onStopClickedSecond() {
        stopwatchModelSecond.stop()
    }
}