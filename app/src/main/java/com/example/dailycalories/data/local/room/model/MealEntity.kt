package com.example.dailycalories.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "MealEntityTable"
)
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val date: String,
    val timeSeconds: Long,
)