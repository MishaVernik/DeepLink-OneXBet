package com.project.onex.appDI

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module

@Module
abstract class BaseActivityModule {

  @PerActivity
  @Binds
  abstract fun activityGlobal(activity: AppCompatActivity): Activity
}