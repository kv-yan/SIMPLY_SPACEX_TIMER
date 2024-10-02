package simply.homework.spacextimer.spacexinfo.domain.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import simply.homework.spacextimer.spacexinfo.domain.api.RocketImagesApi

object RetrofitInstance {
    private
    const val BASE_URL = "https://api.spacexdata.com/"
    val api: RocketImagesApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RocketImagesApi::class.java)
    }
}