package com.jlp.dishwasher.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Details(@SerializedName("productInformation") val productInformation: String) :
    java.io.Serializable
