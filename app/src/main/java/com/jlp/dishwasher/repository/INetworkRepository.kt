package com.jlp.dishwasher.repository

import com.jlp.dishwasher.model.Product
import com.jlp.dishwasher.model.ProductInfo

interface INetworkRepository {

    suspend fun getAllProducts(): ProductInfo

    suspend fun getProduct(id: String): Product
}