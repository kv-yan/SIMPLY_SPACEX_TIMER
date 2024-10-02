package simply.homework.spacextimer.spacexinfo.data.helpers

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

interface FlightDateHelper {
    fun calculateUpcomingFlightDate(firstFlight: String, index: Int): Calendar
}

class FlightDateHelperImpl : FlightDateHelper {
    override fun calculateUpcomingFlightDate(firstFlight: String, index: Int): Calendar {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatter.parse(firstFlight) ?: Date()
        val calendar = Calendar.getInstance().apply { time = date }

        val daysToAdd = when (index) {
            0 -> 3
            1 -> 5
            2 -> 7
            else -> 3 + (index * 2)
        }

        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd)
        calendar.set(Calendar.HOUR_OF_DAY, 15)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }
}


