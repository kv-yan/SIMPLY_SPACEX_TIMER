package simply.homework.spacextimer.spacexinfo.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import simply.homework.spacextimer.spacexinfo.domain.model.RocketImages
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketImagesRepo

interface GetRocketImages {
    suspend fun invoke(): RocketImages
}

class GetInfoUseCaseImpl(private val getRocketImagesRepo: GetRocketImagesRepo) : GetRocketImages {
    override suspend fun invoke(): RocketImages = withContext(Dispatchers.IO){
        getRocketImagesRepo.getImages()
    }
}