package simply.homework.spacextimer.spacexinfo.domain.model

data class RocketDetails(
    val id: String,
    val name: String,
    val company: String,
    val flickrImages: List<String> = emptyList(),
    val country: String,
    val description: String,
    val firstFlight: String,
    val wikipedia: String,
    val costPerLaunch: Int,
    val massKg: Int,
    val heightMeters: Double,
    val active: Boolean,
    val diameterMeters: Double
)
