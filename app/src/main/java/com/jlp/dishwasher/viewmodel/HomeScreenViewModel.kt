package com.jlp.dishwasher.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlp.dishwasher.model.CustomProduct
import com.jlp.dishwasher.model.Product
import com.jlp.dishwasher.repository.NetworkRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeScreenViewModel(private val networkRepository: NetworkRepository) : ViewModel() {
    private val _productsLiveData = MutableLiveData<List<CustomProduct>>()
    val productsLiveData: LiveData<List<CustomProduct>> get() = _productsLiveData

    val errorMessage = MutableLiveData<String>()

    val loading = MutableLiveData<Boolean>()

    private lateinit var mList: List<CustomProduct>


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                loading.postValue(true)
                mList = networkRepository.getAllProducts().products
                Log.e("MLISt", mList.toString())

                if (mList.isNotEmpty()) {
                    mList.forEach { it ->
                        val product =
                            it.productId.let { networkRepository.getProduct(it) }
                        settingCustomValues(product)
                    }
                }

                withContext(Dispatchers.Main) {

                    if (mList.isNotEmpty()) {

                        _productsLiveData.postValue(mList)
                        loading.postValue(false)
                    } else {
                        onError("Network error")

                    }
                }

            } catch (e: Exception) {
                onError(e.message.toString())

            }
        }


    }

    private fun settingCustomValues(product: Product) {
        mList.forEach {
            if (it.productId == product.productId) {
                it.price = product.price
                it.skus = product.skus
                it.code = product.code
                it.details = product.details
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }
}