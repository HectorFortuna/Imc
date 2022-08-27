package com.hectorfortuna.imccalculator.model.usecase

//todo refatorar usecase

interface ImcCalculatorUseCase {
    suspend fun returnImcString(height: Double, weight: Double): Pair<Double,String>
}