package simply.homework.spacextimer.spacexinfo.domain.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import simply.homework.spacextimer.spacexinfo.domain.api.InfoApi
import simply.homework.spacextimer.spacexinfo.domain.api.RetrofitInstance
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetInfoUseCase
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetInfoUseCaseImpl

val domainModule = module {
    factoryOf(::GetInfoUseCaseImpl) { bind<GetInfoUseCase>() }

    single<InfoApi> { RetrofitInstance.api }
}