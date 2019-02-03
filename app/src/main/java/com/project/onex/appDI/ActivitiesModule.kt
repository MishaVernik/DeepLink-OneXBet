package com.project.onex.appDI

import com.project.onex.activityDI.MainActivityModule
import com.project.onex.api.SportSchedulerApiModule
import com.project.onex.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

  @PerActivity
  @ContributesAndroidInjector(modules = [MainActivityModule::class, SportSchedulerApiModule::class])
  abstract fun bindMainActivity(): MainActivity
}