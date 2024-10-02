package simply.homework.spacextimer.spacexinfo.data.ext

import simply.homework.spacextimer.spacexinfo.domain.model.RocketDetails
import java.util.Locale

fun RocketDetails.getRocketDetailsList(): List<Pair<String, String>> {
    return listOf(
        "Company" to company,
        "Country" to country,
        "First Flight" to firstFlight,
        "Active" to "$active".uppercase(Locale.ROOT),
        "Cost Per Launch" to "$costPerLaunch $",
        "Mass " to "$massKg kg",
        "Height" to "$heightMeters m",
        "Diameter" to "$diameterMeters m"
    )
}