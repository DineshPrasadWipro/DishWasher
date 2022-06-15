package com.jlp.dishwasher.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Skus(@SerializedName("media") val media: Media) : java.io.Serializable
