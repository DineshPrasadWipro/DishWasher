package com.jlp.dishwasher.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerializedName("price") val price: Price,
    @SerializedName("productId") val productId: String,
    @SerializedName("skus") val skus: List<Skus>,
    @SerializedName("code") val code: String,
    @SerializedName("details") val details: Details
)
