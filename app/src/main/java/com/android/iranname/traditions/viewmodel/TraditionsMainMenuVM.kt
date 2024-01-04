package com.android.iranname.traditions.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.traditions.db.CityTraditionsDC
import com.android.iranname.traditions.db.CityTraditionsDataBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TraditionsMainMenuVM:ViewModel() {

    private val _citiesList  = MutableStateFlow(emptyList<CityTraditionsDC>())
    val cities: StateFlow<List<CityTraditionsDC>> get() = _citiesList

    private val _loadStatus  = MutableStateFlow("loading")
    val loadStatus: StateFlow<String> get() = _loadStatus

    fun loadCities(context:Context){
        viewModelScope.launch {
            try {
                val localProducts = CityTraditionsDataBase(context).getCityTraditionsDao().getAllCities()

                if(localProducts.isEmpty()){
                    buildLocalProducts(context = context)
                }else{
                    _citiesList.value = localProducts
                    _loadStatus.value = "success"
                }
            }catch (exception : Exception){
                _loadStatus.value = "exception"
            }
        }
    }

    private fun buildLocalProducts(context:Context){
        viewModelScope.launch {
            try {
                val databaseManager = CityTraditionsDataBase(context).getCityTraditionsDao()
                val localProducts = listOf(
                    CityTraditionsDC(
                        name = "همدان",
                        provinceImage = "https://lh5.googleusercontent.com/p/AF1QipOrOfpGls49-Pmw12Z8zOo-98CVrlV7D7G2_fGx=w736-h425-n-k-no",
                        localCustomsImage = "https://www.visitiran.ir/uploaded/2142/de2e_standard_168795640210904.jpg",
                        traditionsImage = "https://www.beytoote.com/images/stories/economic/en1726.jpg",
                        secondTraditionsImage = "https://www.beytoote.com/images/stories/economic/en1723.jpg",
                        thirdTraditionsImage = "https://cdn.asriran.com/files/fa/news/1400/2/7/1207482_996.jpg",
                        fourthTraditionsImage = "https://cdn.isna.ir/d/2022/03/15/2/62209300.jpg?ts=1647357938802",
                        provinceDescription= "",
                        cutomsDescription= "",
                        traditionsDescription= ""
                    ),
                    CityTraditionsDC(
                        name = "همدان",
                        provinceImage = "",
                        localCustomsImage = "",
                        traditionsImage = "",
                        secondTraditionsImage = "",
                        thirdTraditionsImage = "",
                        fourthTraditionsImage = "",
                        provinceDescription= "",
                        cutomsDescription= "",
                        traditionsDescription= ""
                    ),
                    CityTraditionsDC(
                        name = "همدان",
                        provinceImage = "",
                        localCustomsImage = "",
                        traditionsImage = "",
                        secondTraditionsImage = "",
                        thirdTraditionsImage = "",
                        fourthTraditionsImage = "",
                        provinceDescription= "",
                        cutomsDescription= "",
                        traditionsDescription= ""
                    ),
                    CityTraditionsDC(
                        name = "همدان",
                        provinceImage = "",
                        localCustomsImage = "",
                        traditionsImage = "",
                        secondTraditionsImage = "",
                        thirdTraditionsImage = "",
                        fourthTraditionsImage = "",
                        provinceDescription= "",
                        cutomsDescription= "",
                        traditionsDescription= ""
                    )
                )

                localProducts.forEach {
                    databaseManager.newCity(cityTraditionsDC =  it)
                }

                _citiesList.value = localProducts
                _loadStatus.value = "success"

            }catch (exception : Exception){
                _loadStatus.value = "exception"
            }
        }
    }
}