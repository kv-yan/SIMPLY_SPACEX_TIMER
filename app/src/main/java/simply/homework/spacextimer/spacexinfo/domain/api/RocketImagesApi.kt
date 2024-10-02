package simply.homework.spacextimer.spacexinfo.domain.api

import retrofit2.http.GET
import simply.homework.spacextimer.spacexinfo.domain.model.RocketImages


// https://api.spacexdata.com/v4/rockets

interface RocketImagesApi {
    @GET("v4/rockets")
    suspend fun getInfo(): RocketImages
}