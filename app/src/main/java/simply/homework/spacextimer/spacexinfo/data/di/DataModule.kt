package simply.homework.spacextimer.spacexinfo.data.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import simply.homework.spacextimer.spacexinfo.data.helpers.CountdownTimerHelper
import simply.homework.spacextimer.spacexinfo.data.helpers.CountdownTimerHelperImpl
import simply.homework.spacextimer.spacexinfo.data.helpers.FlightDateHelper
import simply.homework.spacextimer.spacexinfo.data.helpers.FlightDateHelperImpl
import simply.homework.spacextimer.spacexinfo.data.repo.GetRocketDetailsRepoImpl
import simply.homework.spacextimer.spacexinfo.data.repo.GetRocketImagesRepoImpl
import simply.homework.spacextimer.spacexinfo.data.repo.GetRocketsRepoImpl
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketDetailsRepo
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketImagesRepo
import simply.homework.spacextimer.spacexinfo.domain.repo.GetRocketsRepo


val dataModule = module {
    factoryOf(::GetRocketImagesRepoImpl) { bind<GetRocketImagesRepo>() }
    factoryOf(::FlightDateHelperImpl) { bind<FlightDateHelper>() }
    factoryOf(::CountdownTimerHelperImpl) { bind<CountdownTimerHelper>() }
    factoryOf(::GetRocketsRepoImpl) { bind<GetRocketsRepo>() }
    factoryOf(::GetRocketDetailsRepoImpl) { bind<GetRocketDetailsRepo>() }


}