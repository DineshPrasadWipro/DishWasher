package com.jlp.dishwasher

import com.jlp.dishwasher.mock.MockNetworkRepository
import com.jlp.dishwasher.model.Product
import com.jlp.dishwasher.model.ProductInfo
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NetworkRepositoryTest {

    private lateinit var mockNetworkRepository: MockNetworkRepository

    private var productList: ProductInfo = mockk(relaxed = true)
    private var product: Product = mockk(relaxed = true)

    @Before
    fun setUp() {
        mockNetworkRepository = mockk(relaxed = true)
    }

    @Test
    fun `Should return product info instance`() {
        every {
            runBlocking {
                mockNetworkRepository.getAllProducts()
            }
        } returns productList
    }

    @Test
    fun `Should return product instance`() {
        every {
            runBlocking {
                mockNetworkRepository.getProduct("")
            }

        } returns product

    }

    @Test
    fun `get product info api should return product info object`() {

        runBlocking {
            mockNetworkRepository.setProductInfo()
            val expected = mockNetworkRepository.getAllProducts()
            Assert.assertNotEquals(productList, expected)
        }

    }

    @Test
    fun `get product api should return product object`() {

        runBlocking {
            mockNetworkRepository.setProduct()
            val expected = mockNetworkRepository.getProduct("")
            Assert.assertNotEquals(product, expected)
        }

    }

}