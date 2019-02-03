package com.project.onex.activityDI

import com.project.onex.appDI.BaseActivityModule
import dagger.Module

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {

}