package simply.homework.spacextimer.spacexinfo.data.repo

import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfo

interface GetInfoRepo {
    suspend fun getInfo(): DomainInfo
}