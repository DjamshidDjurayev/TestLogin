package com.example.djamshiddjuraev.testlogin.ui.login

import com.example.djamshiddjuraev.testlogin.data.repository.LoginRepository
import com.example.djamshiddjuraev.testlogin.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : BaseViewModel<LoginRouter>() {

  fun makeLogin(login: String, password: String) {
    getRouter().showLoading()
    val disposable: Disposable =
        loginRepository.makeLogin(login, password).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
              getRouter().hideLoading()
              getRouter().onSuccess(response)
            }, { error ->
              getRouter().hideLoading()
              getRouter().onError(error)
            })
    compositeDisposable.add(disposable)
  }
}