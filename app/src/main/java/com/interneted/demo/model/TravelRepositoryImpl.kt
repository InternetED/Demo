package com.interneted.demo.model

import com.interneted.demo.model.data.TravelPlace
import com.interneted.demo.model.remote.TravelApi
import com.interneted.demo.model.remote.data.Image

class TravelRepositoryImpl(
    private val api: TravelApi
) : TravelRepository {
    override suspend fun getTravelPlace(page: Int): List<TravelPlace> {
        val travelPlaceResponse = api.fetchTravelPlace("zh-tw", page)

        val formatImageUrl = { image: Image ->
            "${image.src}${image.ext}"
        }

        return travelPlaceResponse.data.map {
            TravelPlace(
                imageUrl = formatImageUrl(it.images.first()),
                title = it.name,
                description = it.introduction,
                link = it.url
            )
        }
    }
}