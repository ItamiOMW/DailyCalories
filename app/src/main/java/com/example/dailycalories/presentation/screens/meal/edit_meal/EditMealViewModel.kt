package com.example.dailycalories.presentation.screens.meal.edit_meal

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
class EditMealViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val foodProductRepository: FoodProductRepository,
    private val validateMeal: ValidateMeal,
    private val application: Application,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var state by mutableStateOf(EditMealState())
        private set


    private val _uiEvent = MutableSharedFlow<EditMealUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    private var meal: Meal? = null

    init {
        viewModelScope.launch {
            savedStateHandle.get<Long>(Screen.MEAL_ID_ARG)?.let { id ->
                getMeal(id)
            } ?: throw Exception("MEAL ID wasn't passed")
        }
    }


    fun onEvent(event: EditMealEvent) {
        when (event) {
            is EditMealEvent.AddMealProduct -> {
                meal?.id?.let { id -> addMealProduct(event.mealProduct, id) }
            }
            is EditMealEvent.DeleteMealProduct -> {
                deleteMealProduct(event.product)
            }
            is EditMealEvent.ChangeMealProductGrams -> {
                state.productToEdit?.let { oldProduct ->
                    changeMealProductGrams(
                        oldProduct,
                        event.editedMealProduct,
                        event.weight
                    )
                }
            }
            is EditMealEvent.ChangeTime -> {
                state = state.copy(timeSeconds = event.time)
            }
            is EditMealEvent.SaveMeal -> {
                meal?.let { meal ->
                    saveMeal(
                        id = meal.id,
                        name = state.name,
                        date = meal.date,
                        timeSeconds = state.timeSeconds,
                        products = state.mealProducts
                    )
                }
            }
            is EditMealEvent.ChangeMealName -> {
                state = state.copy(name = event.name)
            }
            is EditMealEvent.HideEditProductWeightDialog -> {
                state = state.copy(showEditProductWeightDialog = false)
            }
            is EditMealEvent.ShowEditProductWeightDialog -> {
                state = state.copy(
                    showEditProductWeightDialog = true,
                    productToEdit = event.product
                )
            }
            is EditMealEvent.ShowTimePickerDialog -> {
                state = state.copy(showTimePickerDialog = event.show)
            }
        }
    }


    private fun getMeal(id: Long) {
        viewModelScope.launch {
            val getMeal = mealRepository.getMealById(id)
            meal = getMeal
            state = state.copy(
                mealProducts = getMeal.products,
                timeSeconds = getMeal.timeSeconds,
                name = getMeal.name,
            )
            return@launch
        }
    }


    private fun saveMeal(
        id: Long,
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
                id = id,
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
                mealRepository.editMeal(meal)
                _uiEvent.emit(EditMealUiEvent.MealSaved)
                return@launch
            }
            if (validateResult.errorMessageResId != null) {
                _uiEvent.emit(
                    EditMealUiEvent.ShowSnackbar(
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


    private fun addMealProduct(mealProduct: MealFoodProduct, mealId: Long) {
        viewModelScope.launch {
            val newList = state.mealProducts.toMutableList().apply {
                add(mealProduct.copy(mealId = mealId))
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


    private fun changeMealProductGrams(
        oldProduct: MealFoodProduct,
        newProduct: MealFoodProduct,
        grams: Float,
    ) {
        val newList = state.mealProducts.toMutableList()
        val newItem = newProduct.copy(grams = grams)
        val index = newList.indexOf(oldProduct)
        newList.removeAt(index)
        newList.add(index, newItem)
        state = state.copy(mealProducts = newList)
    }


}