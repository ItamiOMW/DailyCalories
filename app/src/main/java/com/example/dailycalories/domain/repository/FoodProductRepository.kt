package com.example.dailycalories.domain.repository

import com.example.dailycalories.domain.model.FoodProductInfo
import com.example.dailycalories.utils.Response

interface FoodProductRepository {

    suspend fun searchFeedProduct(
        query: String,
        page: Int = 1,
        pageSize: Int = 50
    ): Response<List<FoodProductInfo>>

}