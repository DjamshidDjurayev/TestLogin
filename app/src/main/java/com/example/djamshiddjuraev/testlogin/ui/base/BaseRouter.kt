package com.example.djamshiddjuraev.testlogin.ui.base

interface BaseRouter<S, E> {
  fun hideLoading()
  fun showLoading()
  fun onSuccess(s: S)
  fun onError(e: E)
}