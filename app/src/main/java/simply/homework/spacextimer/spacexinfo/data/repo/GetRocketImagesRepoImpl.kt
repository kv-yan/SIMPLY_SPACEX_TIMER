package simply.homework.spacextimer.spacexinfo.data.repo

import simply.homework.spacextimer.spacexinfo.domain.api.RocketImagesApi
import simply.homework.spacextimer.spacexinfo.domain.model.RocketImages
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketImagesRepo

class GetRocketImagesRepoImpl(private val infoApi: RocketImagesApi) : GetRocketImagesRepo {
    override suspend fun getImages(): RocketImages = infoApi.getInfo()
}