package com.project.onex.appDI

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out App> {
    return DaggerAppComponent.builder().create(this)
  }

  override fun onCreate() {
    super.onCreate()
  }
}