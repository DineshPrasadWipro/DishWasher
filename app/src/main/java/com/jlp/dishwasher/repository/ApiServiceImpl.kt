package com.jlp.dishwasher.repository

import android.util.Log
import com.jlp.dishwasher.model.Details
import com.jlp.dishwasher.model.Price
import com.jlp.dishwasher.model.Product
import com.jlp.dishwasher.model.ProductInfo
import com.jlp.dishwasher.util.ApiRoutes
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getProducts(): ProductInfo {
        return try {
            client.get { url(ApiRoutes.BASEURL) }
        } catch (ex: RedirectResponseException) {
            println("Error: ${ex.response.status.description}")
            ProductInfo(emptyList())

        } catch (ex: ClientRequestException) {

            println("Error: ${ex.response.status.description}")
            ProductInfo(emptyList())
        } catch (ex: ServerResponseException) {

            println("Error: ${ex.response.status.description}")
            ProductInfo(emptyList())
        } catch (e: Exception) {
            Log.e("Ex", e.message.toString())
            ProductInfo(emptyList())
        }
    }

    override suspend fun getProduct(id: String): Product {
        return try {
            client.get {
                url(ApiRoutes.PRODUCT_URL + id)
            }
        } catch (ex: RedirectResponseException) {
            println("Error: ${ex.response.status.description}")
            Product(Price(""), "", emptyList(), "", Details(""))

        } catch (ex: ClientRequestException) {

            println("Error: ${ex.response.status.description}")
            Product(Price(""), "", emptyList(), "", Details(""))
        } catch (ex: ServerResponseException) {

            println("Error: ${ex.response.status.description}")
            Product(Price(""), "", emptyList(), "", Details(""))
        } catch (e: Exception) {
            Log.e("Exception", e.message.toString())
            Product(Price(""), "", emptyList(), "", Details(""))
        }
    }

}