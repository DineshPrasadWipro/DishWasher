package com.jlp.dishwasher

import androidx.lifecycle.MutableLiveData
import com.jlp.dishwasher.mock.MockNetworkRepository
import com.jlp.dishwasher.model.*
import com.jlp.dishwasher.repository.NetworkRepository
import com.jlp.dishwasher.viewmodel.HomeScreenViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module

class HomeScreenViewModelTest : TestBase() {

    private val networkRepository: NetworkRepository = mockk(relaxed = true)
    private lateinit var mockNetworkRepository: MockNetworkRepository
    private lateinit var homeScreenViewModel: HomeScreenViewModel


    private val loading = MutableLiveData<Boolean>()
    private val errorMessage = MutableLiveData<String>()


    override val koinModule = module {
        single(override = true) { networkRepository }

    }

    @Before
    override fun setup() {
        super.setup()

        homeScreenViewModel = HomeScreenViewModel(mockk())
        mockNetworkRepository = MockNetworkRepository()
    }

    @Test
    fun `test productList is updating properly`() {


        val productList = ProductInfo(emptyList())

        every {
            runBlocking {
                networkRepository.getAllProducts()
            }
        } returns productList


        homeScreenViewModel.productsLiveData.observeForever {
            Assert.assertEquals(productList.products, it)
        }

    }

    @Test
    fun `test product is updating properly`() {
        val product = Product(Price(""), "", emptyList(), "", Details(""))

        every {
            runBlocking {
                networkRepository.getProduct("12")
            }
        } returns product


        homeScreenViewModel.productsLiveData.observeForever {
            Assert.assertEquals(product, it[0])
        }

    }

    @Test
    fun `test error code is updating properly`() {
        val errorCode = "Error Code"
        homeScreenViewModel.errorMessage.observeForever {
            errorMessage.value = it
        }
        homeScreenViewModel.errorMessage.postValue("Error Code")
        Assert.assertEquals(errorCode, errorMessage.value)


    }

    @Test
    fun `test progress value is updating properly`() {
        loading.value = false
        homeScreenViewModel.loading.observeForever {
            loading.value = true
        }
        homeScreenViewModel.loading.postValue(true)
        Assert.assertEquals(loading.value, true)


    }
}
