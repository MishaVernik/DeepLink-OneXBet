package com.project.onex.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger
import com.facebook.applinks.AppLinkData
import com.project.onex.AppPreferences
import com.project.onex.R
import com.project.onex.adapters.RecyclerAdapter
import com.project.onex.api.API
import com.project.onex.models.Response
import com.project.onex.utils.toMainThread
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import java.util.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

  lateinit var matchesRv: RecyclerView
  @Inject
  lateinit var apiService: API
  lateinit var scheduleAdapter: RecyclerAdapter

  @SuppressLint("CheckResult")
  override fun onCreate(savedInstanceState: Bundle?) {
    if (AppPreferences(this).isOpened()) {
      startActivity<SuccessDeepLinkActivity>()
      finish()
    }

    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    scheduleAdapter = RecyclerAdapter()

    FacebookSdk.sdkInitialize(applicationContext)
    AppEventsLogger.activateApp(this)
    FacebookSdk.setIsDebugEnabled(true)
    FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS)
    initialize()

    apiService.getAllMatches().toMainThread().subscribe({

      scheduleAdapter.addElements(deleteRepeats(it))
      imageView.visibility = View.VISIBLE
      textView.visibility = View.VISIBLE
      image_logo.visibility = View.GONE
    }, { it })

    val extras = intent.extras
    if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
      moveTaskToBack(true)
    }

    AppLinkData.fetchDeferredAppLinkData(this@MainActivity) { appLinkData ->
      if (appLinkData != null && appLinkData.targetUri != null) {
        startActivity(Intent(this@MainActivity, SuccessDeepLinkActivity::class.java))
        finish()
      } else {
    //    startActivity(Intent(this@MainActivity, SuccessDeepLinkActivity::class.java))
     //    finish()
      }
    }
  }

  private fun initialize() {

    matchesRv = findViewById(R.id.rvMatches)
    matchesRv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    matchesRv.itemAnimator = DefaultItemAnimator()

    val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    matchesRv.layoutManager = llm

    matchesRv.adapter = scheduleAdapter
  }

  private fun deleteRepeats(responses: List<Response>): List<Response> {
    val set = HashSet(responses)
    return ArrayList(set)
  }
}
