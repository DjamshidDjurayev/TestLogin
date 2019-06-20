package com.example.djamshiddjuraev.testlogin.ui.login

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.djamshiddjuraev.testlogin.R
import com.example.djamshiddjuraev.testlogin.REGISTRATION_STEP_REQUEST
import com.example.djamshiddjuraev.testlogin.data.model.Login
import com.example.djamshiddjuraev.testlogin.databinding.ActivityLoginBinding
import com.example.djamshiddjuraev.testlogin.ui.base.BaseActivity
import com.example.djamshiddjuraev.testlogin.ui.signup.SignUpActivity
import com.example.djamshiddjuraev.testlogin.utils.CustomPasswordTransformationMethod
import com.example.djamshiddjuraev.testlogin.utils.Utils
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding>(), LoginRouter, View.OnClickListener {

  @Inject lateinit var modelFactory: ViewModelProvider.Factory

  private lateinit var loginViewModel: LoginViewModel

  override fun getLayoutId(): Int = R.layout.activity_login

  override fun getToolbarTitle(): String = getString(R.string.authorization)

  override fun getToolbar(): Toolbar = getDataBinding().toolbar

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    createComponent().inject(this)

    loginViewModel = ViewModelProviders.of(this, modelFactory)[LoginViewModel::class.java]
    loginViewModel.setRouter(this)

    getDataBinding().loginButton.setOnClickListener(this)
    getDataBinding().question.setOnClickListener(this)
    getDataBinding().passwordInput.transformationMethod = CustomPasswordTransformationMethod()
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == android.R.id.home) {
      finish()
    } else if (item?.itemId == R.id.create_item) {
      val intent = Intent(this, SignUpActivity::class.java)
      startActivityForResult(intent, REGISTRATION_STEP_REQUEST)
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK) {
      when(requestCode) {
        REGISTRATION_STEP_REQUEST -> {
          finish()
        }
      }
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.toolbar_login, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onSuccess(s: Login) {
    val response: String = TextUtils.join(",", s.weather)

    Snackbar.make(
        getDataBinding().root,
        response,
        Snackbar.LENGTH_SHORT
    ).show()
  }

  override fun onError(e: Throwable) {
  }

  override fun hideLoading() {
    hideProgressDialog()
    getDataBinding().loginButton.isEnabled = true
  }

  override fun showLoading() {
    showProgressDialog(null, getString(R.string.loading_wait))
  }

  override fun onClick(v: View?) {
    when(v?.id) {
      R.id.login_button -> {
        if (fieldsCorrect()) {
          getDataBinding().loginButton.isEnabled = false
          loginViewModel.makeLogin(
              getDataBinding().loginInput.text.toString(),
              getDataBinding().passwordInput.text.toString())
          Utils.hideKeyboard(currentFocus)
        }
      }

      R.id.question -> {
        if (getDataBinding().passwordInput.transformationMethod is CustomPasswordTransformationMethod) {
          getDataBinding().passwordInput.transformationMethod = HideReturnsTransformationMethod()
        } else {
          getDataBinding().passwordInput.transformationMethod = CustomPasswordTransformationMethod()
        }
        getDataBinding().passwordInput.setSelection(getDataBinding().passwordInput.length())
      }
    }
  }

  private fun fieldsCorrect(): Boolean {
    if (TextUtils.isEmpty(getDataBinding().loginInput.text.toString())) {
      getDataBinding().loginInput.error = getString(R.string.empty_field)
      getDataBinding().loginInput.requestFocus()
      return false
    }

    if (TextUtils.isEmpty(getDataBinding().passwordInput.text.toString())) {
      getDataBinding().passwordInput.error = getString(R.string.empty_field)
      getDataBinding().passwordInput.requestFocus()
      return false
    }

    if (!Utils.isEmailValid(getDataBinding().loginInput.text.toString())) {
      getDataBinding().loginInput.error = getString(R.string.wrong_email)
      getDataBinding().loginInput.requestFocus()
      return false
    }

    if (!Utils.isPasswordValid(getDataBinding().passwordInput.text.toString())) {
      getDataBinding().passwordInput.error = getString(R.string.password_rule)
      getDataBinding().passwordInput.requestFocus()
      return false
    }

    return true
  }

}