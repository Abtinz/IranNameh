package com.android.iranname.traditions.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityTraditionsDao {

    @Insert
    suspend fun newCity(cityTraditionsDC: CityTraditionsDC)

    @Query("SELECT * FROM cityTraditionsDC")
    suspend fun getAllCities(): List<CityTraditionsDC>

}