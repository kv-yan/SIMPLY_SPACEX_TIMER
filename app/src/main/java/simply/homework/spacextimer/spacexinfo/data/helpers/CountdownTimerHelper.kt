package simply.homework.spacextimer.spacexinfo.data.helpers

import kotlinx.coroutines.delay

interface CountdownTimerHelper {
    suspend fun startCountdownTimer(upcomingFlightTime: Long, onUpdate: (Long) -> Unit)
}

class CountdownTimerHelperImpl : CountdownTimerHelper {
    override suspend fun startCountdownTimer(upcomingFlightTime: Long, onUpdate: (Long) -> Unit) {
        var remainingTime = upcomingFlightTime
        while (remainingTime > 0) {
            onUpdate(remainingTime)
            delay(1000L)
            remainingTime -= 1000L
        }
    }
}
