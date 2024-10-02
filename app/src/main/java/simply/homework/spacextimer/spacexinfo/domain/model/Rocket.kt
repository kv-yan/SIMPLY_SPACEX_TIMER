package simply.homework.spacextimer.spacexinfo.domain.model

data class Rocket(
    val id: String,
    val name: String,
    var imageList: List<String> = emptyList()
)
