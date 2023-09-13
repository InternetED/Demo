package com.interneted.demo.model.remote

import com.interneted.demo.model.remote.data.TravelPlaceResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Creator: ED
 * Date: 2023/9/13 10:20
 * Mail: salahayo3192@gmail.com
 *
 * **/
interface TravelApi {

//    https://www.travel.taipei/open-api/zh-tw/Attractions/All?page=1
    @GET("{language}/Attractions/All")
    @Headers(
        "accept: application/json"
    )
    suspend fun fetchTravelPlace(
        @Path("language") language: String,
        @Query("page") page: Int,
        @Query("categoryIds") categoryIds : String = "12"
    ): TravelPlaceResponse

}