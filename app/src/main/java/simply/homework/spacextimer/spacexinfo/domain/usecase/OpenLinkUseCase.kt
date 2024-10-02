package simply.homework.spacextimer.spacexinfo.domain.usecase

import simply.homework.spacextimer.spacexinfo.domain.repo.LinkRepository


interface OpenLinkUseCase {
    suspend operator fun invoke(url: String)
}

class OpenLinkUseCaseImpl(private val repository: LinkRepository) : OpenLinkUseCase {
    override suspend operator fun invoke(url: String) {
        repository.openLinkInBrowser(url)
    }
}
