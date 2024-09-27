package simply.homework.spacextimer.spacexinfo.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import simply.homework.spacextimer.spacexinfo.domain.model.DomainInfo
import simply.homework.spacextimer.spacexinfo.data.repo.GetInfoRepo

interface GetInfoUseCase {
    suspend fun invoke(): DomainInfo
}

class GetInfoUseCaseImpl(private val getInfoRepo: GetInfoRepo) : GetInfoUseCase {
    override suspend fun invoke(): DomainInfo = withContext(Dispatchers.IO){
        getInfoRepo.getInfo()
    }
}