package com.example.djamshiddjuraev.testlogin.ui.registration

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.djamshiddjuraev.testlogin.R
import com.example.djamshiddjuraev.testlogin.data.model.Login
import com.example.djamshiddjuraev.testlogin.databinding.ActivitySignupBinding
import com.example.djamshiddjuraev.testlogin.ui.base.BaseActivity

class RegistrationActivity: BaseActivity<ActivitySignupBinding, RegistrationViewModel>(), RegistrationRouter, View.OnClickListener {

  override fun getLayoutId(): Int = R.layout.activity_signup

  override fun getToolbarTitle(): String? = getString(R.string.registration)

  override fun getToolbar(): Toolbar? = getDataBinding().toolbar

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    createComponent().inject(this)

    getViewModel().setRouter(this)

    getDataBinding().cancelButton.setOnClickListener(this)
    getDataBinding().signupButton.setOnClickListener(this)
  }

  override fun hideLoading() {
    hideProgressDialog()
    getDataBinding().signupButton.isEnabled = true
  }

  override fun showLoading() {
    showProgressDialog(null, getString(R.string.loading_wait))
  }

  override fun onSuccess(s: Login) {
    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
    setResult(Activity.RESULT_OK)
    finish()
  }

  override fun onError(e: Throwable) {
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == android.R.id.home) {
      finish()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onClick(v: View?) {
    when(v?.id) {
      R.id.signup_button -> {
        getDataBinding().signupButton.isEnabled = false
        getViewModel().signUp("test", "test")
      }

      R.id.cancel_button -> finish()
    }
  }

}