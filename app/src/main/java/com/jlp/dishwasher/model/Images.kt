package com.jlp.dishwasher.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Images(@SerializedName("urls") val urls: List<String>) : java.io.Serializable
