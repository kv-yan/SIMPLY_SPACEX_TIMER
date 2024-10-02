package simply.homework.spacextimer.spacexinfo.domain.model

data class RocketImage(
    val id: String,
    val name: String,
    val flickr_images: List<String>,
)