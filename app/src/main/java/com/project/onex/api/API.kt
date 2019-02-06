package com.project.onex.api

import com.project.onex.models.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface API {

    @GET("GetAllFeedGames")
    fun getAllMatches() : Observable<List<Response>>

}
