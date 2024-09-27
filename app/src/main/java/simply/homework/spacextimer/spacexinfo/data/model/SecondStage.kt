package simply.homework.spacextimer.spacexinfo.data.model

data class SecondStage(
    val burn_time_sec: Int,
    val engines: Int,
    val fuel_amount_tons: Double,
    val reusable: Boolean,
)