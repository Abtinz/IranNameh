package com.android.iranname.shops.viewmodel.faqs

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.shops.db.faqs.ProductFAQsDC
import com.android.iranname.shops.db.faqs.ProductFAQsDataBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductFAQsVM:ViewModel() {

    private val _faqsList  = MutableStateFlow(emptyList<ProductFAQsDC>())
    val faqs: StateFlow<List<ProductFAQsDC>> get() = _faqsList

    private val _loadStatus  = MutableStateFlow("loading")
    val loadStatus: StateFlow<String> get() = _loadStatus


    fun loadProductsFaqs(context:Context,productId:Int){
        viewModelScope.launch {
            try {
                val localFaqs = ProductFAQsDataBase(context).getProductFAQsDao()
                    .getFAQsByProductId(productId = productId)

                if(localFaqs.isEmpty()){
                    buildLocalProductFAQs(
                        context = context,
                        productId = productId
                    )
                }else{
                    _faqsList.value = localFaqs
                    _loadStatus.value = "success"
                }
            }catch (exception : Exception){
                _loadStatus.value = "exception"
            }
        }
    }

    private fun buildLocalProductFAQs(context:Context,productId:Int){
        viewModelScope.launch {
            try {
                val databaseManager =  ProductFAQsDataBase(context).getProductFAQsDao()
                val localFAQs = listOf(
                   ProductFAQsDC(
                       question = "ایا تضمین کیفیت برای این محصول وجود دارد؟",
                       answer = "با سلام، بله برای تمام محصولات فروشگاه تضمین کیفیت وجود دارد و در صورت عدم رضایت شما تا یک ماه کالای فروخته شده قابلیت مرجوعی دارد",
                       productId = productId
                   ),
                    ProductFAQsDC(
                        question = "نحوه ارسال این کالا چطور است؟",
                        answer = "ارسال این کالا با پیک برای شهر تهران و با پست سراری برای باقی نقاط کشور انجام می شود",
                        productId = productId
                    )
                )

                localFAQs.forEach {
                    databaseManager.newFAQs(productFAQsDC =  it)
                }

                _faqsList.value = localFAQs
                _loadStatus.value = "success"

            }catch (exception : Exception){
                _loadStatus.value = "exception"
            }
        }
    }
}