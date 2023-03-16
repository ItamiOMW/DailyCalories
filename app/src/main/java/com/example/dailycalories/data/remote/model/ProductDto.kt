package com.example.dailycalories.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("product_name") val productName: String?,
    @SerializedName("nutriments") val nutriments: NutrimentsDto
)
