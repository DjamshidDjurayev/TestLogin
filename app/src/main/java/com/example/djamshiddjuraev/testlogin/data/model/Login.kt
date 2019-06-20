package com.example.djamshiddjuraev.testlogin.data.model

import com.google.gson.annotations.SerializedName

class Login(
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("weather")
    val weather: List<Weather>
)