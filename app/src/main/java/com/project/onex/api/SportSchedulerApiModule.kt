package com.project.onex.api

import com.project.onex.appDI.PerActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [ApiModule::class])
class SportSchedulerApiModule {

    @PerActivity
    @Provides
    fun provideLoginApiService(retrofit: Retrofit) = retrofit.create(API::class.java)

}