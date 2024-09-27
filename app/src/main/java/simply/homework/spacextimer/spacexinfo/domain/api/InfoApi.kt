package simply.homework.spacextimer.spacexinfo.domain.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfo


// https://api.spacexdata.com/v4/rockets

interface InfoApi {
    @GET("v4/rockets")
    suspend fun getInfo(): DomainInfo
}


object RetrofitInstance {
    private
    const val BASE_URL = "https://api.spacexdata.com/"
    val api: InfoApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(InfoApi::class.java)
    }
}