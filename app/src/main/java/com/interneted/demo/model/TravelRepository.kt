package com.interneted.demo.model

import com.interneted.demo.model.data.TravelPlace

/**
 * Creator: ED
 * Date: 2023/9/13 10:19
 * Mail: salahayo3192@gmail.com
 *
 * **/
interface TravelRepository {

    suspend fun getTravelPlace(page: Int): List<TravelPlace>
}