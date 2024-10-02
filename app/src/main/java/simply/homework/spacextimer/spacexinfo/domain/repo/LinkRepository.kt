package simply.homework.spacextimer.spacexinfo.domain.repo

interface LinkRepository {
    suspend fun openLinkInBrowser(url: String)

}

