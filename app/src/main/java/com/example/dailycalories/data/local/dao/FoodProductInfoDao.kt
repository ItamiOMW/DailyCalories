package com.example.dailycalories.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.dailycalories.data.local.model.FoodProductInfoEntity

@Dao
interface FoodProductInfoDao {


    //Todo add paging library before implement GET
//    fun getFoodProductInfos()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<FoodProductInfoEntity>)


}