package com.android.iranname.traditions.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CityTraditionsDC(
    val name :String,
    val provinceImage : String,
    val localCustomsImage : String,
    val traditionsImage : String,
    val secondTraditionsImage : String,
    val thirdTraditionsImage : String,
    val fourthTraditionsImage : String,
    val provinceDescription: String,
    val cutomsDescription: String,
    val traditionsDescription: String,
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
}
