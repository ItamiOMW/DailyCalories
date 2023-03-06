package com.example.dailycalories.di

import com.example.dailycalories.data.repository.CalorieCounterRepositoryImpl
import com.example.dailycalories.data.repository.MealRepositoryImpl
import com.example.dailycalories.data.repository.UserRepositoryImpl
import com.example.dailycalories.domain.repository.CalorieCounterRepository
import com.example.dailycalories.domain.repository.MealRepository
import com.example.dailycalories.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideCalorieCounterRepository(
        calorieCounterRepositoryImpl: CalorieCounterRepositoryImpl,
    ): CalorieCounterRepository = calorieCounterRepositoryImpl


    @Singleton
    @Provides
    fun provideMealRepository(
        mealRepositoryImpl: MealRepositoryImpl,
    ): MealRepository = mealRepositoryImpl


    @Singleton
    @Provides
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository = userRepositoryImpl

}