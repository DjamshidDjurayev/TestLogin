package com.example.djamshiddjuraev.testlogin.ui.signup

import com.example.djamshiddjuraev.testlogin.data.repository.SignUpRepository
import com.example.djamshiddjuraev.testlogin.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class SignUpViewModel @Inject constructor(private val signUpRepository: SignUpRepository) : BaseViewModel<SignUpRouter>() {

  fun signUp(login: String, password: String) {
    getRouter().showLoading()
    val disposable: Disposable =
        signUpRepository.signUp(login, password).observeOn(AndroidSchedulers.mainThread())
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