package com.project.onex.appDI

import com.project.onex.api.SportSchedulerApiModule
import com.project.onex.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

  @PerActivity
  @ContributesAndroidInjector(modules = [BaseActivityModule::class, SportSchedulerApiModule::class])
  abstract fun bindMainActivity(): MainActivity
}