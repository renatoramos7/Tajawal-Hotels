package com.renatoramos.tajawal.data.store.remote.network

import com.renatoramos.tajawal.data.model.HotelsModel
import io.reactivex.Observable
import retrofit2.http.GET

interface NetworkService {

    @GET("hotels_exercise.json")
    fun getHotels(): Observable<HotelsModel>
}
