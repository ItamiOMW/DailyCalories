package com.example.dailycalories.data.remote.model

import com.google.gson.annotations.SerializedName

data class NutrimentsDto(
    @SerializedName("carbohydrates_100g") val carbohydrates100g: Float,
    @SerializedName("energy-kcal_100g") val energyKcal100g: Float,
    @SerializedName("fat_100g") val fat100g: Float,
    @SerializedName("proteins_100g") val proteins100g: Float,
)