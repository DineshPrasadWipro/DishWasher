package com.jlp.dishwasher.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class
CustomProduct(
    @SerializedName("productId") val productId: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
) : java.io.Serializable {

    var price: Price = Price("")
    var skus: List<Skus> = ArrayList<Skus>()
    var details: Details = Details("")
    var code: String = ""
}

