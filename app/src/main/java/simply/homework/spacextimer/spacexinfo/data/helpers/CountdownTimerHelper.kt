package simply.homework.spacextimer.spacexinfo.data.helpers

import kotlinx.coroutines.delay

interface CountdownTimerHelper {
    suspend fun startCountdownTimer(upcomingFlightTime: Long, onUpdate: (Long) -> Unit)
}

class CountdownTimerHelperImpl : CountdownTimerHelper {
    override suspend fun startCountdownTimer(upcomingFlightTime: Long, onUpdate: (Long) -> Unit) {
        while (true) {
            val remainingTime = upcomingFlightTime - System.currentTimeMillis()
            if (remainingTime <= 0) {
                println("CountdownTimer Timer finished!")
                break
            }
            onUpdate(remainingTime) // Update the remaining time
            delay(1000L)
        }
    }
}