package simply.homework.spacextimer.spacexinfo.domain.repo

import simply.homework.spacextimer.spacexinfo.domain.model.Rocket

interface GetRocketsRepo {
    suspend fun getRockets(): List<Rocket>
}