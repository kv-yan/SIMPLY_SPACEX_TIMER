package simply.homework.spacextimer.spacexinfo.data.repo

import simply.homework.RocketsQuery
import simply.homework.spacextimer.spacexinfo.domain.apollo.ApolloInstance
import simply.homework.spacextimer.spacexinfo.domain.model.Rocket
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketImagesRepo
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketsRepo

class GetRocketsRepoImpl(
    private val getRocketImagesRepo: GetRocketImagesRepo,
    private val apolloInstance: ApolloInstance
) : GetRocketsRepo {
    val apolloClient = apolloInstance.apolloClient

    override suspend fun getRockets(): List<Rocket> {
        // Step 1: Fetch the rocket data from the Apollo Client
        val rocketsResponse = apolloClient.query(RocketsQuery()).execute()

        // Step 2: Map the GraphQL response to a list of Rocket objects
        val rockets = rocketsResponse.data?.rockets?.mapNotNull { rocketData ->
            rocketData?.let {
                Rocket(
                    id = it.id.orEmpty(),
                    name = it.name.orEmpty()
                )
            }
        } ?: emptyList()

        // Step 3: Fetch rocket images from the REST API
        val rocketImages = getRocketImagesRepo.getImages()

        // Step 4: Combine the rockets with their corresponding images
        val rocketsWithImages = rockets.map { rocket ->
            // Find the matching images for the current rocket by comparing their ids
            val matchingImages = rocketImages.find { it.id == rocket.id }?.flickr_images ?: emptyList()

            // Return a copy of the rocket with the imageList populated
            rocket.copy(imageList = matchingImages)
        }

        return rocketsWithImages
    }
}
