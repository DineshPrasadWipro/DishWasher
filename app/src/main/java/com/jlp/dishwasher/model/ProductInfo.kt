package com.jlp.dishwasher.model

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class ProductInfo(@SerializedName("products") val products: List<CustomProduct>)


