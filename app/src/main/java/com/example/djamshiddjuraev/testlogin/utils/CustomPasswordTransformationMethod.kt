package com.example.djamshiddjuraev.testlogin.utils

import android.text.method.PasswordTransformationMethod
import android.view.View

open class CustomPasswordTransformationMethod : PasswordTransformationMethod() {

  override fun getTransformation(source: CharSequence, view: View): CharSequence {
    return PasswordCharSequence(super.getTransformation(source, view))
  }

  private class PasswordCharSequence(
      val transformation: CharSequence
  ) : CharSequence by transformation {
    override fun get(index: Int): Char = if (transformation[index] == '\u2022') {
      '⬤'
    } else {
      transformation[index]
    }
  }
}