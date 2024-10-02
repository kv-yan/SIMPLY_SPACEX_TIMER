package simply.homework.spacextimer.spacexinfo.data.repo

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import simply.homework.RocketQuery // here
import simply.homework.spacextimer.spacexinfo.domain.apollo.ApolloInstance
import simply.homework.spacextimer.spacexinfo.domain.model.RocketDetails
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketDetailsRepo

private const val TAG = "GetRocketDetailsImpl"

class GetRocketDetailsRepoImpl(
    private val apolloInstance: ApolloInstance
) : GetRocketDetailsRepo {
    private val apolloClient =apolloInstance.apolloClient

    override suspend fun getRocketDetails(rocketId: String): RocketDetails? =
        withContext(Dispatchers.IO) {
            try {
                val response = apolloClient.query(RocketQuery(rocketId)).execute()
                val rocketData = response.data?.rocket

                return@withContext rocketData?.let {
                    RocketDetails(
                        id = it.id.orEmpty(),
                        name = it.name.orEmpty(),
                        company = it.company.orEmpty(),
                        country = it.country.orEmpty(),
                        description = it.description.orEmpty(),
                        firstFlight = it.first_flight.toString().orEmpty(),
                        wikipedia = it.wikipedia.orEmpty(),
                        costPerLaunch = it.cost_per_launch.toString().toInt(),
                        massKg = it.mass?.kg.toString().toInt(),
                        heightMeters = it.height?.meters?.toFloat()?.toDouble() ?: 0.0,
                        active = it.active ?: false,
                        diameterMeters = it.diameter?.meters?.toFloat()?.toDouble() ?: 0.0
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "getRocketDetails: EXCEPTION ${e.message} ", )
                e.printStackTrace()
                return@withContext null
            }
        }
}
