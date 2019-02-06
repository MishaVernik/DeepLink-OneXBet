package com.project.onex.ui.activities

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import com.project.onex.AppPreferences
import com.project.onex.R
import com.project.onex.utils.Constants.LINK_SUCCESS
import kotlinx.android.synthetic.main.activity_deep_link_success.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast
import kotlin.concurrent.thread

class SuccessDeepLinkActivity : BaseActivity() {
  var dialog: ProgressDialog? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    onPostRequest(LINK_SUCCESS)
  }

  override fun layoutID(): Int {
    return R.layout.activity_deep_link_success
  }

  override fun showWebView(url: String) {

    web_view.settings.javaScriptEnabled = true
    if (Build.VERSION.SDK_INT >= 21) {
      web_view.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    }
    web_view.loadUrl(url)

    thread {
      Thread.sleep(5000)
      runOnUiThread {
        web_view.visibility = View.VISIBLE
        hideDialog()
      }
    }

    web_view.webViewClient = object : WebViewClient() {

      override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        AppPreferences(this@SuccessDeepLinkActivity).isOpened(true)
      }

      override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        view?.loadUrl(url)
        return false
      }

      override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
        web_view.visibility = View.GONE
      }

      @TargetApi(android.os.Build.VERSION_CODES.M)
      override fun onReceivedError(view: WebView, req: WebResourceRequest, rerr: WebResourceError) {
        onReceivedError(view, rerr.errorCode, rerr.description.toString(), req.url.toString())
      }
    }
  }

  private fun showDialog(c: Context) {
    try {
      if (dialog != null) {
        if (!dialog!!.isShowing) {
          dialog?.show()
        }
      } else {
        dialog = c.indeterminateProgressDialog("Загрузка")
        dialog?.show()
      }
    } catch (e: Exception) {
      toast("Ошибка при загрузке")
    }
  }

  private fun hideDialog() {
    image_logo.visibility = View.GONE
//    try {
//      dialog?.let {
//        if (it.isShowing) {
//          it.dismiss()
//        }
//      }
//    } catch (e: Exception) {
//      toast("Ошибка")
//    }
  }

  override fun onBackPressed() {
    finishAffinity()
  }
}
