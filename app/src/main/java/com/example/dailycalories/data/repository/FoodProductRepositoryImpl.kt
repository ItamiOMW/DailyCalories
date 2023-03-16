package com.example.dailycalories.data.repository

import com.example.dailycalories.data.mapper.toListFoodProductInfo
import com.example.dailycalories.data.remote.OpenFoodApiService
import com.example.dailycalories.domain.model.food_product.FoodProductInfo
import com.example.dailycalories.domain.repository.FoodProductRepository
import com.example.dailycalories.domain.utils.Response
import javax.inject.Inject

class FoodProductRepositoryImpl @Inject constructor(
    private val openFoodApi: OpenFoodApiService,
) : FoodProductRepository {

    override suspend fun searchFeedProduct(
        query: String,
        page: Int,
        pageSize: Int,
    ): Response<List<FoodProductInfo>> {
        return try {
            val productsSearchResponse = openFoodApi.searchFood(
                query.trim(),
                page,
                pageSize
            )
            val products = productsSearchResponse.products.toListFoodProductInfo()
            Response.success(products)
        } catch (e: Exception) {
            Response.failed(e.message.toString())
        }
    }

}