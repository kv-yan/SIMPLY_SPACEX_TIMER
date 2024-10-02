package simply.homework.spacextimer.spacexinfo.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import simply.homework.spacextimer.spacexinfo.domain.model.Rocket
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketsRepo

interface GetRocketsUseCase {
    suspend fun invoke(): List<Rocket>
}

class GetRocketsUseCaseImpl(private val rocketRepository: GetRocketsRepo) : GetRocketsUseCase {
    override suspend fun invoke(): List<Rocket> = withContext(Dispatchers.IO) {
        rocketRepository.getRockets()
    }
}