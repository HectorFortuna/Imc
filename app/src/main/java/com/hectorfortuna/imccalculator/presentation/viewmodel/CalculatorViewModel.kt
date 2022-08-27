package com.hectorfortuna.imccalculator.presentation.viewmodel

import androidx.lifecycle.*
import com.hectorfortuna.imccalculator.model.usecase.ImcCalculatorUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class CalculatorViewModel(
    private val useCase: ImcCalculatorUseCase,
) : ViewModel() {

    private val _imcReturn = MutableLiveData<Pair<Double,String>>()
    val imcReturn: LiveData<Pair<Double,String>>
        get() = _imcReturn


    fun returnImcString(height: Double, weight: Double) {
        viewModelScope.launch {
            try {
                _imcReturn.value = useCase.returnImcString(height, weight)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }
}


