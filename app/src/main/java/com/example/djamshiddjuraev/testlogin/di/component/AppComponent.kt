package com.example.djamshiddjuraev.testlogin.di.component

import com.example.djamshiddjuraev.testlogin.App
import com.example.djamshiddjuraev.testlogin.di.module.AppModule
import com.example.djamshiddjuraev.testlogin.di.module.NetworkModule
import com.example.djamshiddjuraev.testlogin.di.module.ViewModelModule
import com.example.djamshiddjuraev.testlogin.ui.login.LoginActivity
import com.example.djamshiddjuraev.testlogin.ui.signup.SignUpActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: App)

    fun inject(loginActivity: LoginActivity)

    fun inject(signUpActivity: SignUpActivity)
}