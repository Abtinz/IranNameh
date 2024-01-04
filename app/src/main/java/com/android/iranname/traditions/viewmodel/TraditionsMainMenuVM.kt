package com.android.iranname.traditions.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.R
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
                        provinceDescription= context.getString(R.string.about_hamedan),
                        cutomsDescription= context.getString(R.string.cloth_hamedan),
                        traditionsDescription= context.getString(R.string.hamedan_traditions)
                    ),
                    CityTraditionsDC(
                        name = "شیراز",
                        provinceImage = "https://cdn.alibaba.ir/ostorage/alibaba-mag/wp-content/uploads/2021/04/%D8%B4%D8%A7%D8%AE%D8%B5.jpg",
                        localCustomsImage = "https://namnamak.com/images/up/153/904.jpg",
                        traditionsImage = "https://mosafersalam.com/wp-content/uploads/2019/04/adab-va-rosom-noroz-dar-shiraz3.jpg",
                        secondTraditionsImage = "https://umagcdn.utravs.com/JournalsImages/571/860x550/%D9%81%D8%B1%D9%87%D9%86%DA%AF%20%D8%A8%D9%88%D9%85%DB%8C%20%D8%AF%D8%B1%20%D8%B4%DB%8C%D8%B1%D8%A7%D8%B2.jpg",
                        thirdTraditionsImage = "https://www.alaedin.travel/Files/blogs/75456c9d-644a-454a-a5e2-7c9e5bcbaeb3.jpg",
                        fourthTraditionsImage = "https://www.eligasht.com/Blog/wp-content/uploads/2022/12/shiraz-4.jpg",
                        provinceDescription= context.getString(R.string.about_shiraz),
                        cutomsDescription= context.getString(R.string.cloth_shiraz),
                        traditionsDescription= context.getString(R.string.shiraz_traditions)
                    ),
                    CityTraditionsDC(
                        name = "مشهد",
                        provinceImage = "https://images.kojaro.com/2021/1/791654bc-7b12-4c9b-820a-ffabd618dd10-840x560.jpg",
                        localCustomsImage = "https://jazebeha.com/wp-content/uploads/2020/02/%D9%84%D8%A8%D8%A7%D8%B3-%D9%85%D8%AD%D9%84%DB%8C-%D8%AC%D8%A7%D9%87%D8%A7%DB%8C-%D9%85%D8%AE%D8%AA%D9%84%D9%81-%D8%A7%D8%B3%D8%AA%D8%A7%D9%86-%D8%AE%D8%B1%D8%A7%D8%B3%D8%A7%D9%86-%D8%B1%D8%B6%D9%88-213842.jpg",
                        traditionsImage = "https://www.taksetareh.ir/wp-content/uploads/2022/08/mashhad-customs-1024x576.jpg",
                        secondTraditionsImage = "https://nikapoosh.com/wp-content/uploads/2022/11/Martyrdom-ceremony-of-Imam-Reza.jpg",
                        thirdTraditionsImage = "https://www.taksetareh.ir/wp-content/uploads/2022/08/Customs-of-Mashhad-people2.jpg",
                        fourthTraditionsImage = "https://www.zavaran.com/hardwrite/img/orginal/18/xNPBM0ClrG7qo1fti2gbac13rhiim1c5g1of0t637.jpg.jpg",
                        provinceDescription= context.getString(R.string.about_mashad),
                        cutomsDescription= context.getString(R.string.cloth_mashad),
                        traditionsDescription= context.getString(R.string.mashad_traditions)
                    ),
                    CityTraditionsDC(
                        name = "سنندج",
                        provinceImage = "https://www.flytoday.ir/blog/wp-content/uploads/2023/05/Header.jpg",
                        localCustomsImage = "https://aradbranding.com/fa/uploads/topics/mceu_4723317111676982086583.jpg",
                        traditionsImage = "https://www.mizan.news/wp-content/uploads/2022/01/1544825_658.jpg",
                        secondTraditionsImage = "https://www.vilajar.com/Dashboard/GetArticleImage/27488",
                        thirdTraditionsImage = "http://ir-handicrafts.ir/img/marivan/culture/%D9%81%D8%B1%D9%87%D9%86%DA%AF-%D8%A8%D9%88%D9%85%DB%8C-%DA%A9%D8%B1%D8%AF%D8%B3%D8%AA%D8%A7%D9%86.jpg",
                        fourthTraditionsImage = "http://ir-handicrafts.ir/img/marivan/culture/%D9%81%D8%B1%D9%87%D9%86%DA%AF-%D8%A8%D9%88%D9%85%DB%8C-%D9%85%D8%B1%DB%8C%D9%88%D8%A7%D9%86-%D9%87%D9%87-%D9%84%D9%BE%D8%B1%DA%A9%DB%8C-%DA%A9%D8%B1%D8%AF%DB%8C.jpg",
                        provinceDescription= context.getString(R.string.about_sandaj),
                        cutomsDescription= context.getString(R.string.cloth_sandaj),
                        traditionsDescription= context.getString(R.string.sandaj_traditions)
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