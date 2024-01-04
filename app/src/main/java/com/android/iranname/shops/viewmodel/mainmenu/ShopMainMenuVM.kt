package com.android.iranname.shops.viewmodel.mainmenu

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.R
import com.android.iranname.shops.db.ShopProductDataBase
import com.android.iranname.shops.model.ProductsDC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShopMainMenuVM:ViewModel() {

    private val _productList  = MutableStateFlow(emptyList<ProductsDC>())
    val products: StateFlow<List<ProductsDC>> get() = _productList

    private val _loadStatus  = MutableStateFlow("loading")
    val loadStatus: StateFlow<String> get() = _loadStatus

    fun loadProducts(context:Context){
        viewModelScope.launch {
            try {
                val localProducts = ShopProductDataBase(context).getProductDao().getAllProducts()

                if(localProducts.isEmpty()){
                    buildLocalProducts(context = context)
                }else{
                    _productList.value = localProducts
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
                val databaseManager = ShopProductDataBase(context).getProductDao()
                val localProducts = listOf(
                    ProductsDC(
                        name = "کاسه سفالی ساخت لالجین",
                        image = "https://tahooneh.ir/wp-content/uploads/2018/11/Clay-and-Ceramic-Dastnegar-03-602-cc33d5.jpg",
                        price = 100000.0,
                        description = context.getString(R.string.sofal_lalejin)
                    ),
                    ProductsDC(
                        name = "زعفران خراسان رضوی(یک مثقال)",
                        image = "https://media.eghtesadonline.com/d/2023/11/28/4/869558.jpg?ts=1701158532000",
                        price = 550000.0,
                        description = context.getString(R.string.zaferan_description)
                    )
                )

                localProducts.forEach {
                    databaseManager.newProduct(productsDC =  it)
                }

                _productList.value = localProducts
                _loadStatus.value = "success"

            }catch (exception : Exception){
                _loadStatus.value = "exception"
            }
        }
    }
}