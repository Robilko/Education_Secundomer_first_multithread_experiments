package com.example.secundomer.model

import com.example.secundomer.model.time.StopwatchStateHolder
import com.example.secundomer.util.DEFAULT_TIME
import com.example.secundomer.util.DELAY_UPDATE_TIME
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * У нас есть класс StopwatchStateHolder, который предоставляет
все необходимые функции для управления секундомером.
Если считать миллисекунды в главном потоке приложения (а так и будет по умолчанию),
то экран будет постоянно подвисать на время всех вычислений текущего времени, обновления
состояния и т. д.
Чтобы этого не происходило, нам нужно написать класс, который выполняет все
операции асинхронно. То есть перенести работу  функций в другой поток. Тут
как раз и пригодится StateFlow.

Функции pause() и stop() останавливают работу корутины, потому что
секундомер в это время не нужно обновлять. А ещё stop() обнуляет таймер. Обратите внимание, что
работа корутины останавливается через функцию cancelChildren(), а не cancel(). Функция
cancelChildren() останавливает работу, но сохраняет scope, чтобы в нем можно было запускать новые
корутины. Это нужно как раз на тот случай, если пользователь нажал не Стоп, а Пауза на
секундомере.
 */
class StopwatchModelImpl(
    private val stopwatchStateHolder: StopwatchStateHolder,
    private val scope: CoroutineScope
) : StopwatchModel{

    private var job: Job? = null
    private val mutableTicker = MutableStateFlow(DEFAULT_TIME)
    override val ticker: StateFlow<String> = mutableTicker

    /**
     * Функция  создает coroutine job, которая будет
    обновлять время для секундомера асинхронно. Она состоит из бесконечного цикла, в котором каждые
    20 миллисекунд(интервал можно задать любой в переменной DELAY_UPDATE_TIME) проверяется, нужна ли еще эта работа. Обновление происходит через StateFlow,
    который запускается из UI.
     */
    override fun start() {
        if (job == null) startJob()
        stopwatchStateHolder.start()
    }

    private fun startJob() {
        scope.launch {
            while (isActive) {
                mutableTicker.value = stopwatchStateHolder.getStringTimeRepresentation()
                delay(DELAY_UPDATE_TIME)
            }
        }
    }

    override fun pause() {
        stopwatchStateHolder.pause()
        stopJob()
    }

    override fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun clearValue() {
        mutableTicker.value = DEFAULT_TIME
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
        job = null
    }
}