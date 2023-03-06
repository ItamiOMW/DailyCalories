package com.example.dailycalories.data.remote

import com.example.dailycalories.data.remote.model.ProductsSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodApiService {


    @GET("cgi/search.pl?search_simple=1&json=1&action=process&fields=product_name,nutriments,image_front_thumb_url")
    suspend fun searchFood(
        @Query("search_terms") query: String,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 50,
    ): ProductsSearchResponseDto


    companion object {

        const val BASE_URL = "https://world.openfoodfacts.org/"

    }
}