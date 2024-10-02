package simply.homework.spacextimer.spacexinfo.data.repo

import simply.homework.RocketsQuery
import simply.homework.spacextimer.spacexinfo.domain.apollo.ApolloInstance
import simply.homework.spacextimer.spacexinfo.domain.model.Rocket
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketImagesRepo
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketsRepo

class GetRocketsRepoImpl(
    private val getRocketImagesRepo: GetRocketImagesRepo,
    apolloInstance: ApolloInstance
) : GetRocketsRepo {
    private val apolloClient = apolloInstance.apolloClient

    override suspend fun getRockets(): List<Rocket> {
        val rocketsResponse = apolloClient.query(RocketsQuery()).execute()

        val rockets = rocketsResponse.data?.rockets?.mapNotNull { rocketData ->
            rocketData?.let {
                Rocket(
                    id = it.id.orEmpty(),
                    name = it.name.orEmpty()
                )
            }
        } ?: emptyList()

        val rocketImages = getRocketImagesRepo.getImages()

        val rocketsWithImages = rockets.map { rocket ->
            val matchingImages = rocketImages.find { it.id == rocket.id }?.flickr_images ?: emptyList()
            rocket.copy(imageList = matchingImages)
        }

        return rocketsWithImages
    }
}
