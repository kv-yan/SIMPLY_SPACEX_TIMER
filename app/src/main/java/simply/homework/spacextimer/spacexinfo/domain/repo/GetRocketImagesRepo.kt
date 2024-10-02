package simply.homework.spacextimer.spacexinfo.domain.repo

import simply.homework.spacextimer.spacexinfo.domain.model.RocketImages

interface GetRocketImagesRepo {
    suspend fun getImages(): RocketImages
}