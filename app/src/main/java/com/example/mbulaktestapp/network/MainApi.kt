package com.example.mbulaktestapp.network

import com.example.mbulaktestapp.model.CheckCodeResult
import com.example.mbulaktestapp.model.CheckPhoneResult
import com.example.mbulaktestapp.model.MainResponse
import com.example.mbulaktestapp.model.CountryResult
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {

    @GET("api/app/listAvailableCountry/?token=oYyxhIFgJjAb")
    fun getPosts(): Observable<MainResponse<List<CountryResult>>>

    @POST("api/app/checkPhone/?token=oYyxhIFgJjAb")
    @FormUrlEncoded
    fun checkPhone(@Field("phone")phoneNumber:String): Observable<MainResponse<CheckPhoneResult>>

    @POST("api/app/checkPhone/?token=oYyxhIFgJjAb")
    @FormUrlEncoded
    fun checkCode(
        @Field("id")id: Int,
        @Field("phone")code: Int): Observable<MainResponse<CheckCodeResult>>
}