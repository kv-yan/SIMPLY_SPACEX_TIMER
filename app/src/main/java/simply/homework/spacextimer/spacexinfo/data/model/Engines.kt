package simply.homework.spacextimer.spacexinfo.data.model

data class Engines(
    val engine_loss_max: Int,
    val layout: String,
    val number: Int,
    val propellant_1: String,
    val propellant_2: String,
    val thrust_to_weight: Double,
    val type: String,
    val version: String
)