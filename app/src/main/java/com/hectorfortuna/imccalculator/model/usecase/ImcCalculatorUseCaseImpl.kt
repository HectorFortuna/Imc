package com.hectorfortuna.imccalculator.model.usecase

class ImcCalculatorUseCaseImpl: ImcCalculatorUseCase {
    override suspend fun returnImcString(height: Double, weight: Double): Pair <Double,String> {
        val calculateHeight = height * height
        val calculateImc = weight.div(calculateHeight)
        val imc: Double = calculateImc
        val imcFormatted = "%.2f".format(imc).toDouble()

         return Pair(imcFormatted,when{
            imc >= 0 && imc < 18.5 -> "Seu imc é ${imcFormatted}, o seu estado é abaixo do peso"
            imc in 18.5..24.99 -> "Seu imc é ${imcFormatted}, o seu estado é Normal"
            imc in 25.0..29.99 -> "Seu imc é ${imcFormatted} o seu estado é de Sobrepeso"
            imc in 30.0..39.99 -> "Seu imc é ${imcFormatted}, o seu estado é de Obesidade"
            imc >= 40 -> "Seu imc é ${imcFormatted}, o seu estado é de Obesidade 1"
             else -> {""}
         })
    }

}
    