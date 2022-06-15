package com.jlp.dishwasher.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Price(@SerializedName("now") val now: String) : java.io.Serializable
