package com.example.djamshiddjuraev.testlogin.data.repository

import com.example.djamshiddjuraev.testlogin.data.api.AppService
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val appService: AppService) {
  fun signUp(login: String, password: String) = appService.makeLogin(login, password)
}