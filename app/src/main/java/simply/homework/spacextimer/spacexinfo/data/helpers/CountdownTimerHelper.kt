package simply.homework.spacextimer.spacexinfo.data.helpers

import kotlinx.coroutines.delay

interface CountdownTimerHelper {
    suspend fun startCountdownTimer(upcomingFlightTime: Long, onUpdate: (Long) -> Unit)
}

class CountdownTimerHelperImpl : CountdownTimerHelper {
    override suspend fun startCountdownTimer(upcomingFlightTime: Long, onUpdate: (Long) -> Unit) {
        var remainingTime = upcomingFlightTime
        while (remainingTime > 0) {
            onUpdate(remainingTime) // Update the remaining time
            delay(1000L) // Wait for 1 second
            remainingTime -= 1000L // Decrease by 1000ms (1 second)
        }
    }
}
