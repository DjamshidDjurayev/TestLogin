package com.example.djamshiddjuraev.testlogin.data.api

import com.example.djamshiddjuraev.testlogin.data.model.Login
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
  @GET("/weather?q=London%2Cuk")
  fun makeLogin(@Query("test") login: String, @Query("test2") password: String) : Flowable<Login>

  @GET("/weather?q=London%2Cuk")
  fun signUp(@Query("test") login: String, @Query("test2") password: String) : Flowable<Login>
}