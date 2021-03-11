package ru.ric_kos.dogapirxjavaexample.model.data

import io.reactivex.Observable
import retrofit2.http.GET
interface MessageApiService {

    @GET("breeds/image/random")
    fun getMessage(): Observable<MessageApiDto>
}