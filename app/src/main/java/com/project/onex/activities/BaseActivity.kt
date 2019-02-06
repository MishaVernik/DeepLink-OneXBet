package com.project.onex.ui.activities

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.project.onex.PostRequset.PostRequset
import com.project.onex.utils.Constants.LINK_FAILURE
import com.project.onex.utils.Constants.LINK_SUCCESS
import dagger.android.support.DaggerAppCompatActivity
import okhttp3.*
import java.io.IOException

abstract class BaseActivity : AppCompatActivity(), PostRequset {

  abstract fun layoutID(): Int

  open fun showWebView(url: String) {

  }

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutID())
  }

  //просто лень улучшать, пускай будет так
  override fun onPostRequest(link: String) {
    OkHttpClient().newCall( Request.Builder().url(link).build()).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
      }

      @Throws(IOException::class)
      override fun onResponse(call: Call, response: Response) {
        if (response.isSuccessful) {
          runOnUiThread {
            if (link.contentEquals(LINK_SUCCESS)) {
              showWebView(link)
            } else if (link.contentEquals(LINK_FAILURE)) {
              showWebView(link)
            }
          }
        }
      }
    })
  }
}