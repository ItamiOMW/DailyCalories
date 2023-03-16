package com.example.dailycalories.presentation.screens.meal.add_meal

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.domain.model.meal.MealFoodProduct
import com.example.dailycalories.domain.repository.FoodProductRepository
import com.example.dailycalories.domain.repository.MealRepository
import com.example.dailycalories.domain.usecase.meal_validation.ValidateMeal
import com.example.dailycalories.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMealViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val foodProductRepository: FoodProductRepository,
    private val validateMeal: ValidateMeal,
    private val application: Application,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var state by mutableStateOf(AddMealState())
        private set


    private val _uiEvent = MutableSharedFlow<AddMealUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var date: String? = null

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>(Screen.DATE_ARG)?.let { d ->
                date = d
            } ?: throw Exception("Date wasn't passed")
        }
    }


    fun onEvent(event: AddMealEvent) {
        when (event) {
            is AddMealEvent.AddMealProduct -> {
                addMealProduct(event.mealProduct)
            }
            is AddMealEvent.DeleteMealProduct -> {
                deleteMealProduct(event.product)
            }
            is AddMealEvent.ChangeMealProductGrams -> {
                changeMealProductGrams(event.mealProduct, event.weight)
            }
            is AddMealEvent.ChangeTime -> {
                state = state.copy(timeSeconds = event.time)
            }
            is AddMealEvent.SaveMeal -> {
                saveMeal(
                    name = state.name,
                    date = date.toString(),
                    timeSeconds = state.timeSeconds,
                    products = state.mealProducts
                )
            }
            is AddMealEvent.ChangeMealName -> {
                state = state.copy(name = event.name)
            }
            is AddMealEvent.HideEditProductWeightDialog -> {
                state = state.copy(showEditProductWeightDialog = false)
            }
            is AddMealEvent.ShowEditProductWeightDialog -> {
                state = state.copy(
                    showEditProductWeightDialog = true,
                    productToEdit = event.product
                )
            }
        }
    }

    private fun saveMeal(
        name: String,
        date: String,
        timeSeconds: Long,
        products: List<MealFoodProduct>,
    ) {
        viewModelScope.launch {
            val calories = products.sumOf { it.cals.toDouble() }.toFloat()
            val carbs = products.sumOf { it.carbs.toDouble() }.toFloat()
            val fat = products.sumOf { it.fat.toDouble() }.toFloat()
            val proteins = products.sumOf { it.proteins.toDouble() }.toFloat()
            val meal = Meal(
                name = name.trim(),
                date = date,
                timeSeconds = timeSeconds,
                carbs = carbs,
                calories = calories,
                proteins = proteins,
                fat = fat,
                products = products
            )
            val validateResult = validateMeal.invoke(meal)
            if (validateResult.successful) {
                mealRepository.addMeal(meal)
                _uiEvent.emit(AddMealUiEvent.MealSaved)
                return@launch
            }
            if (validateResult.errorMessageResId != null) {
                _uiEvent.emit(
                    AddMealUiEvent.ShowSnackbar(
                        application.getString(
                            validateResult.errorMessageResId
                        )
                    )
                )
                return@launch
            }
            return@launch
        }
    }


    private fun addMealProduct(mealProduct: MealFoodProduct) {
        viewModelScope.launch {
            val newList = state.mealProducts.toMutableList().apply {
                add(mealProduct)
            }
            state = state.copy(mealProducts = newList)
        }
    }


    private fun deleteMealProduct(product: MealFoodProduct) {
        val newList = state.mealProducts.toMutableList().apply {
            remove(product)
        }
        state = state.copy(mealProducts = newList)
    }


    private fun changeMealProductGrams(product: MealFoodProduct, grams: Float) {
        val newList = state.mealProducts.toMutableList()
        val newItem = product.copy(grams = grams)
        val index = newList.indexOf(product)
        newList.removeAt(index)
        newList.add(index, newItem)
        state = state.copy(mealProducts = newList)
    }


}