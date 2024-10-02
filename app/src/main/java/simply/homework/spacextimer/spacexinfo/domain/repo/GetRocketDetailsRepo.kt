package simply.homework.spacextimer.spacexinfo.domain.repo

import simply.homework.spacextimer.spacexinfo.domain.model.RocketDetails

interface GetRocketDetailsRepo {
    suspend fun getRocketDetails(rocketId: String): RocketDetails?
}