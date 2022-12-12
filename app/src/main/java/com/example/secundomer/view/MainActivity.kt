package com.example.secundomer.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.secundomer.databinding.ActivityMainBinding
import com.example.secundomer.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    /** Ленивая инициализация viewmodel с использованием ViewModelProvider и фабрики, для того чтобы наша вьюмодель жила пока наша активити не закроется окончательно */
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonsOnClickListeners()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tickerFirst.collect {
                    binding.textTimeFirstStopwatch.text = it
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tickerSecond.collect {
                    binding.textTimeSecondStopwatch.text = it
                }
            }
        }

    }

    private fun setButtonsOnClickListeners() {
        with(binding) {
            buttonStartFirstStopwatch.setOnClickListener {
                viewModel.onStartClickedFirst()
            }
            buttonPauseFirstStopwatch.setOnClickListener {
                viewModel.onPauseClickedFirst()
            }
            buttonStopFirstStopwatch.setOnClickListener {
                viewModel.onStopClickedFirst()
            }
            buttonStartSecondStopwatch.setOnClickListener {
                viewModel.onStartClickedSecond()
            }
            buttonPauseSecondStopwatch.setOnClickListener {
                viewModel.onPauseClickedSecond()
            }
            buttonStopSecondStopwatch.setOnClickListener {
                viewModel.onStopClickedSecond()
            }
        }
    }
}