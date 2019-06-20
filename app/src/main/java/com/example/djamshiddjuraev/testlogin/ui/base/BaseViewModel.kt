package com.example.djamshiddjuraev.testlogin.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

open class BaseViewModel<R> : ViewModel() {

  val compositeDisposable: CompositeDisposable = CompositeDisposable()
  private lateinit var router: WeakReference<R>

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }

  fun setRouter(t: R) {
    router = WeakReference(t)
  }

  fun getRouter(): R = router.get() as R
}