package com.project.onex

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class AppPreferences(private val context: Context) {
  private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

  private val URL_IS_OPENED = "URL_IS_OPENED"

  fun isOpened(isOpened: Boolean) = prefs.edit().putBoolean(URL_IS_OPENED, isOpened).apply()

  fun isOpened() = prefs.getBoolean(URL_IS_OPENED, false)

}