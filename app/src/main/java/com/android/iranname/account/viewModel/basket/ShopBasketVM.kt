package com.android.iranname.account.viewModel.basket

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.account.db.basket.BasketProductDC
import com.android.iranname.account.db.basket.BasketProductDataBase
import com.android.iranname.shops.model.ProductsDC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShopBasketVM:ViewModel() {

    private val _productList  = MutableStateFlow(emptyList<BasketProductDC>())
    val products: StateFlow<List<BasketProductDC>> get() = _productList

    private val _loadStatus  = MutableStateFlow("loading")
    val loadStatus: StateFlow<String> get() = _loadStatus

    private val _addToBasketStatus  = MutableStateFlow("")
    val addToBasketStatus: StateFlow<String> get() = _addToBasketStatus

    private val _removeFromBasketStatus  = MutableStateFlow("loading")
    val removeFromStatus: StateFlow<String> get() = _removeFromBasketStatus

    fun loadBasket(context:Context){
        viewModelScope.launch {
            try {
                _productList.value =  BasketProductDataBase(context).getBasketProductDao().getBasket()
                _loadStatus.value = "success"
            }catch (exception : Exception){
                _loadStatus.value = "exception"
            }
        }
    }

    fun addToBasket(context:Context,productsDC: ProductsDC){
        viewModelScope.launch {
            try {
                val product = BasketProductDC(
                    name= productsDC.name,
                    image = productsDC.image,
                    description = productsDC.description,
                    price = productsDC.price
                )
                BasketProductDataBase(context).getBasketProductDao().newProduct(product)
                _addToBasketStatus.value = "success"
            }catch (exception : Exception){
                _addToBasketStatus.value = "exception"
            }
        }
    }

    fun removeFromBasket(context:Context,basketProductDC: BasketProductDC){
        viewModelScope.launch {
            try {
                _addToBasketStatus.value = "loading"
                BasketProductDataBase(context).getBasketProductDao().deleteProduct(basketProductDC)
                _removeFromBasketStatus.value = "success"
            }catch (exception : Exception){
                _removeFromBasketStatus.value = "exception"
            }
        }
    }

}