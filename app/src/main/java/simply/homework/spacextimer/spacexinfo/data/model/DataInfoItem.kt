package simply.homework.spacextimer.spacexinfo.data.model

data class DataInfoItem(
    val active: Boolean,
    val boosters: Int,
    val company: String,
    val cost_per_launch: Int,
    val country: String,
    val description: String,
    val engines: Engines,
    val first_flight: String,
    val first_stage: FirstStage,
    val flickr_images: List<String>,
    val id: String,
    val name: String,
    val second_stage: SecondStage,
    val stages: Int,
    val success_rate_pct: Int,
    val type: String,
    val wikipedia: String
)