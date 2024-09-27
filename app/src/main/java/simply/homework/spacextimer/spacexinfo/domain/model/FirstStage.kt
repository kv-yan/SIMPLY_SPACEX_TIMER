package simply.homework.spacextimer.spacexinfo.domain.model

data class FirstStage(
    val burn_time_sec: Int,
    val engines: Int,
    val fuel_amount_tons: Double,
    val reusable: Boolean,
)