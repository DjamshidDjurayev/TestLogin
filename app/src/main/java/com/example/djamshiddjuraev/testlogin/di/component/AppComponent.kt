package com.example.djamshiddjuraev.testlogin.di.component

import android.databinding.ViewDataBinding
import com.example.djamshiddjuraev.testlogin.App
import com.example.djamshiddjuraev.testlogin.di.module.AppModule
import com.example.djamshiddjuraev.testlogin.di.module.NetworkModule
import com.example.djamshiddjuraev.testlogin.di.module.ViewModelModule
import com.example.djamshiddjuraev.testlogin.ui.base.BaseActivity
import com.example.djamshiddjuraev.testlogin.ui.base.BaseViewModel
import com.example.djamshiddjuraev.testlogin.ui.login.LoginActivity
import com.example.djamshiddjuraev.testlogin.ui.registration.RegistrationActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: App)

    fun inject(baseActivity: BaseActivity<ViewDataBinding, BaseViewModel<*>>)

    fun inject(loginActivity: LoginActivity)

    fun inject(registrationActivity: RegistrationActivity)
}