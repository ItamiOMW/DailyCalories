package com.example.dailycalories.presentation.screens.meal.search_product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.domain.repository.FoodProductRepository
import com.example.dailycalories.domain.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val foodProductRepository: FoodProductRepository,
) : ViewModel() {


    var state by mutableStateOf(SearchProductState())
        private set

    private val _uiEvent = MutableSharedFlow<SearchProductUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    private var getProductsJob: Job? = null


    fun onEvent(event: SearchProductEvent) {
        when (event) {
            is SearchProductEvent.ChangeQuery -> {
                state = state.copy(query = event.query)
                getProductsJob?.cancel()
                getProductsJob = viewModelScope.launch {
                    delay(300)
                    getProductInfos(event.query)
                }
            }
            is SearchProductEvent.ShowAddProductDialog -> {
                state = state.copy(
                    showAddProductDialog = true,
                    selectedProduct = event.product
                )
            }
            is SearchProductEvent.HideAddProductDialog -> {
                state = state.copy(selectedProduct = null, showAddProductDialog = false)
            }
        }
    }


    private fun getProductInfos(query: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            state = when (val response = foodProductRepository.searchFeedProduct(query)) {
                is Response.Failed -> {
                    _uiEvent.emit(SearchProductUiEvent.ShowSnackbar(response.message))
                    state.copy(isLoading = false)
                }
                is Response.Success -> {
                    state.copy(products = response.data, isLoading = false)
                }
            }
        }
    }

}