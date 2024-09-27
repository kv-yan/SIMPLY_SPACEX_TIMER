package simply.homework.spacextimer.spacexinfo.domain.repo

import simply.homework.spacextimer.spacexinfo.data.repo.GetInfoRepo
import simply.homework.spacextimer.spacexinfo.domain.api.InfoApi
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfo

class GetInfoRepoImpl(private val infoApi: InfoApi) : GetInfoRepo {
    override suspend fun getInfo(): DomainInfo = infoApi.getInfo()

}