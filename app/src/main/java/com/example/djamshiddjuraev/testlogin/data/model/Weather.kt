package com.example.djamshiddjuraev.testlogin.data.model

import com.google.gson.annotations.SerializedName

class Weather(
    @SerializedName("id")
    val id: Long,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
) {
    override fun toString(): String {
        return "$main,$description"
    }
}