package com.jlp.dishwasher.mock

import com.jlp.dishwasher.model.Details
import com.jlp.dishwasher.model.Price
import com.jlp.dishwasher.model.Product
import com.jlp.dishwasher.model.ProductInfo
import com.jlp.dishwasher.repository.INetworkRepository
import io.mockk.mockk

class MockNetworkRepository() : INetworkRepository {

    private var productList: ProductInfo = mockk(relaxed = true)
    var product: Product = mockk(relaxed = true)


    override suspend fun getAllProducts(): ProductInfo {
        return productList
    }

    override suspend fun getProduct(id: String): Product {
        return product
    }

    fun setProduct() {
        product = Product(Price(""), "", emptyList(), "", Details(""))

    }

    fun setProductInfo(){
        productList = ProductInfo(emptyList())
    }

}
