package com.example.djamshiddjuraev.testlogin.data.model

import com.google.gson.annotations.SerializedName

class Coord(
    @SerializedName("lon")
    val lon: Float,
    @SerializedName("lat")
    val lat: Float
)