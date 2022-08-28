package com.hectorfortuna.imccalculator.model.usecase

import com.hectorfortuna.imccalculator.model.Constants.NORMAL_WEIGHT
import com.hectorfortuna.imccalculator.model.Constants.OBESITY
import com.hectorfortuna.imccalculator.model.Constants.OVERWEIGHT
import com.hectorfortuna.imccalculator.model.Constants.SEVERAL_OBESITY
import com.hectorfortuna.imccalculator.model.Constants.UNDER_WEIGHT

class ImcCalculatorUseCaseImpl: ImcCalculatorUseCase {
    override suspend fun returnImcString(height: Double, weight: Double): Pair <Double,String> {
        val calculateHeight = height * height
        val calculateImc = weight.div(calculateHeight)
        val imc: Double = calculateImc
        val imcFormatted = "%.2f".format(imc).toDouble()

         return Pair(imcFormatted,when{
            imc >= 0 && imc < 18.5 -> String.format(UNDER_WEIGHT, imcFormatted)
            imc in 18.5..24.99 ->  String.format(NORMAL_WEIGHT, imcFormatted)
            imc in 25.0..29.99 -> String.format(OVERWEIGHT, imcFormatted)
            imc in 30.0..39.99 -> String.format(OBESITY, imcFormatted)
            imc >= 40 -> String.format(SEVERAL_OBESITY, imcFormatted)
             else -> {""}
         })
    }
}

