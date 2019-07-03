package com.example.djamshiddjuraev.testlogin.di.module

import dagger.Module
import android.arch.lifecycle.ViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import android.arch.lifecycle.ViewModelProvider
import com.example.djamshiddjuraev.testlogin.di.ViewModelFactory
import com.example.djamshiddjuraev.testlogin.di.ViewModelKey
import com.example.djamshiddjuraev.testlogin.ui.login.LoginViewModel
import com.example.djamshiddjuraev.testlogin.ui.registration.RegistrationViewModel

@Module
abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(LoginViewModel::class)
  internal abstract fun bindloginViewModel(loginViewModel: LoginViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(RegistrationViewModel::class)
  internal abstract fun bindSignUpViewModel(registrationViewModel: RegistrationViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}