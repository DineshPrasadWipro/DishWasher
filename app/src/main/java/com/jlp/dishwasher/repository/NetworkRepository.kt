package com.jlp.dishwasher.repository

import com.jlp.dishwasher.model.Product
import com.jlp.dishwasher.model.ProductInfo


class NetworkRepository : INetworkRepository {
    private val apiService by lazy {
        ApiService.create()
    }


    override suspend fun getAllProducts(): ProductInfo {
        return apiService.getProducts()
    }


    override suspend fun getProduct(id: String): Product {
        return apiService.getProduct(id)
    }


}
