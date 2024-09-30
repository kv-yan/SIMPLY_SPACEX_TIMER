package simply.homework.spacextimer.spacexinfo.data.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import simply.homework.spacextimer.spacexinfo.data.helpers.CountdownTimerHelper
import simply.homework.spacextimer.spacexinfo.data.helpers.CountdownTimerHelperImpl
import simply.homework.spacextimer.spacexinfo.data.helpers.FlightDateHelper
import simply.homework.spacextimer.spacexinfo.data.helpers.FlightDateHelperImpl
import simply.homework.spacextimer.spacexinfo.data.repo.GetInfoRepo
import simply.homework.spacextimer.spacexinfo.domain.repo.GetInfoRepoImpl


val dataModule = module {
    factoryOf(::GetInfoRepoImpl) { bind<GetInfoRepo>() }
    factoryOf(::GetInfoRepoImpl) { bind<GetInfoRepo>() }
    factoryOf(::FlightDateHelperImpl) { bind<FlightDateHelper>() }
    factoryOf(::CountdownTimerHelperImpl) { bind<CountdownTimerHelper>() }
}