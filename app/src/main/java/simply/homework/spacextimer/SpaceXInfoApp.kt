package simply.homework.spacextimer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import simply.homework.spacextimer.spacexinfo.data.di.dataModule
import simply.homework.spacextimer.spacexinfo.domain.di.domainModule
import simply.homework.spacextimer.spacexinfo.presentation.di.presentationModule

class SpaceXInfoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SpaceXInfoApp)
            modules(presentationModule, domainModule, dataModule)
        }
    }
}