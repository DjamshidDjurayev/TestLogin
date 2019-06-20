package com.example.djamshiddjuraev.testlogin.di.module

import com.example.djamshiddjuraev.testlogin.data.api.AppService
import com.example.djamshiddjuraev.testlogin.data.repository.LoginRepository
import com.example.djamshiddjuraev.testlogin.data.repository.SignUpRepository
import dagger.Module
import dagger.Provides

@Module
open class AppModule {
  @Provides
  fun provideLoginRepository(appService: AppService): LoginRepository {
    return LoginRepository(appService)
  }

  @Provides
  fun provideSignUpRepository(appService: AppService): SignUpRepository {
    return SignUpRepository(appService)
  }
}