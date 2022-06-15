package com.jlp.dishwasher.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Media(@SerializedName("images") val images: Images) : java.io.Serializable
