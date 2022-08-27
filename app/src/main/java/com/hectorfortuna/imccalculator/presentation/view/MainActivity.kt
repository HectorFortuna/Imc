package com.hectorfortuna.imccalculator.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hectorfortuna.imccalculator.databinding.ActivityMainBinding
import com.hectorfortuna.imccalculator.model.usecase.ImcCalculatorUseCase
import com.hectorfortuna.imccalculator.model.usecase.ImcCalculatorUseCaseImpl
import com.hectorfortuna.imccalculator.presentation.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var useCase: ImcCalculatorUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        useCase = ImcCalculatorUseCaseImpl()
        viewModel = CalculatorViewModel(useCase)

        getViewCalculator()
        observeVMEvents()

    }

    private fun getViewCalculator() {
        binding.calculateButton.setOnClickListener {
            binding.apply {
               val height = heightEditText.text.toString()
               val weight = weightEdit.text.toString()
                if (height.isNotEmpty() && weight.isNotEmpty()) {
                    returnImcString(height.toDouble(), weight.toDouble())
                }
            }
        }
    }

    private fun observeVMEvents() {
        viewModel.imcReturn.observe(this) {
            setView(it.second)
            setArrows(it.first)

        }
    }

    private fun setView(result: String) {
        binding.apply {
            textResults.visibility = View.VISIBLE
            textResults.text = result
        }
    }

    private fun returnImcString(height: Double, weight: Double) {
        viewModel.returnImcString(height, weight)
    }

    private fun setArrows(imc: Double) {
        binding.apply {
            when {
                imc >= 0 && imc < 18.5 -> {
                    setAllInvisible()
                    arrow1.visibility = View.VISIBLE
                }
                imc in 18.5..24.99 -> {
                    setAllInvisible()
                    arrow2.visibility = View.VISIBLE
                }
                imc in 25.0..29.99 -> {
                    setAllInvisible()
                    arrow3.visibility = View.VISIBLE
                }
                imc in 30.0..39.99 -> {
                    setAllInvisible()
                    arrow4.visibility = View.VISIBLE
                }
                imc >= 40 -> {
                    setAllInvisible()
                    arrow5.visibility = View.VISIBLE
                }

            }
        }

    }

    private fun ActivityMainBinding.setAllInvisible() {
        arrow1.visibility = View.INVISIBLE
        arrow2.visibility = View.INVISIBLE
        arrow3.visibility = View.INVISIBLE
        arrow4.visibility = View.INVISIBLE
        arrow5.visibility = View.INVISIBLE
    }
}
