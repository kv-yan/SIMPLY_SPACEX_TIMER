package simply.homework.spacextimer.spacexinfo.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import simply.homework.spacextimer.spacexinfo.domain.model.RocketDetails
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketDetailsRepo

interface GetRocketDetailsUseCase {
    suspend fun invoke(rocketId: String): RocketDetails?
}

class GetRocketDetailsUseCaseImpl(private val getRocketDetails: GetRocketDetailsRepo) :
    GetRocketDetailsUseCase {
    override suspend fun invoke(rocketId: String): RocketDetails? = withContext(Dispatchers.IO) {
        getRocketDetails.getRocketDetails(rocketId)
    }
}