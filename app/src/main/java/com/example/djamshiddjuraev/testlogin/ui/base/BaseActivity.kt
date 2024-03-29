package com.example.djamshiddjuraev.testlogin.ui.base

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.djamshiddjuraev.testlogin.R
import com.example.djamshiddjuraev.testlogin.di.component.AppComponent
import com.example.djamshiddjuraev.testlogin.di.component.DaggerAppComponent
import com.example.djamshiddjuraev.testlogin.di.module.AppModule
import com.example.djamshiddjuraev.testlogin.di.module.NetworkModule
import android.os.Build
import android.view.View


abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity() {
  private lateinit var viewDataBinding: D
  private lateinit var appComponent: AppComponent
  private lateinit var progressDialog: ProgressDialog

  override fun onCreate(savedInstanceState: Bundle?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      val decor = window.decorView
      decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    super.onCreate(savedInstanceState)
    viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
    setToolBar()
  }

  private fun setToolBar() {
    if (getToolbar() != null) {
      setSupportActionBar(getToolbar())
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
      supportActionBar?.setHomeButtonEnabled(true)
      supportActionBar?.title = getToolbarTitle()
      supportActionBar?.setHomeAsUpIndicator(R.mipmap.ic_arrow_back_black_24_px)
    }
  }

  @LayoutRes
  abstract fun getLayoutId(): Int

  @Nullable
  abstract fun getToolbarTitle(): String?

  @Nullable
  abstract fun getToolbar(): Toolbar?

  fun getDataBinding(): D = viewDataBinding

  fun createComponent(): AppComponent {
    appComponent = DaggerAppComponent.builder().networkModule(NetworkModule()).appModule(AppModule()).build()
    return appComponent
  }

  fun showProgressDialog(title: String?, message: String?) {
    progressDialog = ProgressDialog(this)
    progressDialog.isIndeterminate = true
    progressDialog.setCancelable(true)
    progressDialog.setCanceledOnTouchOutside(true)
    progressDialog.setTitle(title)
    progressDialog.setMessage(message)
    progressDialog.show()
  }

  fun hideProgressDialog() {
    if (progressDialog.isShowing) {
      progressDialog.cancel()
    }
  }
}