package com.example.dailycalories.presentation.screens.meal.meal_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.domain.model.meal.MealFoodProduct
import com.example.dailycalories.domain.repository.MealRepository
import com.example.dailycalories.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var state by mutableStateOf(MealDetailState())
        private set


    private var meal: Meal? = null

    init {
        viewModelScope.launch {
            savedStateHandle.get<Long>(Screen.MEAL_ID_ARG)?.let { id ->
                getMeal(id)
            } ?: throw Exception("MEAL ID wasn't passed")
        }
    }


    fun onEvent(event: MealDetailEvent) {
        when (event) {
            is MealDetailEvent.AddMealProduct -> {
                meal?.id?.let { id -> addMealProduct(event.mealProduct, id) }
            }
            is MealDetailEvent.ChangeMealProductGrams -> {
                state.productToEdit?.let { oldProduct ->
                    changeMealProductGrams(
                        oldProduct,
                        event.weight
                    )
                }
            }
            is MealDetailEvent.HideEditProductWeightDialog -> {
                state = state.copy(showEditProductWeightDialog = false)
            }
            is MealDetailEvent.ShowEditProductWeightDialog -> {
                state = state.copy(
                    showEditProductWeightDialog = true,
                    productToEdit = event.product
                )
            }

        }
    }


    private fun getMeal(id: Long) {
        viewModelScope.launch {
            mealRepository.getMealByIdFlow(id).collect { updatedMeal ->
                meal = updatedMeal
                state = state.copy(
                    mealProducts = updatedMeal.products,
                    timeSeconds = updatedMeal.timeSeconds,
                    name = updatedMeal.name,
                )
            }
        }
    }


    private fun addMealProduct(mealProduct: MealFoodProduct, mealId: Long) {
        viewModelScope.launch {
            mealRepository.addMealFoodProductToMeal(
                mealId = mealId,
                mealFoodProduct = mealProduct
            )
        }
    }


    private fun changeMealProductGrams(
        oldProduct: MealFoodProduct,
        grams: Float,
    ) {
        viewModelScope.launch {
            mealRepository.editMealFoodProductWeight(grams, oldProduct)
        }
    }


}