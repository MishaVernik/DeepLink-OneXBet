package com.project.onex.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger
import com.facebook.applinks.AppLinkData
import com.project.onex.api.API

import com.project.onex.R
import com.project.onex.adapters.RecyclerAdapter
import com.project.onex.models.Response
import com.project.onex.utils.toMainThread

import javax.inject.Inject

import dagger.android.support.DaggerAppCompatActivity
import java.util.ArrayList
import java.util.HashSet

class MainActivity : DaggerAppCompatActivity() {

    lateinit var rvMatches: RecyclerView
    @Inject
    lateinit var apiService : API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        setContentView(R.layout.activity_main)
        FacebookSdk.setIsDebugEnabled(true)
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS)
        initialize()
        apiService.getAllMatches().toMainThread().subscribe({

            setAdapter(it)
        },{

            it
        })


        val extras = intent.extras
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true)
        }

        AppLinkData.fetchDeferredAppLinkData(this@MainActivity) { appLinkData ->
            var myIntent: Intent? = null
            if (appLinkData != null && appLinkData.targetUri != null) {

              //  myIntent = Intent(this@MainActivity, SuccessDeepLinkActivity::class.java)
             //   startActivity(myIntent)

            } else {

            //    myIntent = Intent(this@MainActivity, FailureDeepLinkActivity::class.java)
             //   startActivity(myIntent)
            }
        }
    }

    private fun initialize() {
        rvMatches = findViewById(R.id.rvMatches)
    }

    private fun setAdapter(response: List<Response>) {

        val adapterCountries = RecyclerAdapter(deleteRepeats(response))

        rvMatches.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvMatches.itemAnimator = DefaultItemAnimator()

        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMatches.layoutManager = llm

        rvMatches.adapter = adapterCountries
    }

    private fun deleteRepeats(responses: List<Response>): List<Response> {
        val set = HashSet(responses)
        return ArrayList(set)
    }
}
