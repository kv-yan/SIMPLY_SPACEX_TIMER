package simply.homework.spacextimer.spacexinfo.domain.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import simply.homework.spacextimer.spacexinfo.domain.api.RocketImagesApi
import simply.homework.spacextimer.spacexinfo.domain.apollo.ApolloInstance
import simply.homework.spacextimer.spacexinfo.domain.retrofit.RetrofitInstance
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetInfoUseCaseImpl
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetRocketDetailsUseCase
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetRocketDetailsUseCaseImpl
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetRocketImages
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetRocketsUseCase
import simply.homework.spacextimer.spacexinfo.domain.usecase.GetRocketsUseCaseImpl


val domainModule = module {
    factoryOf(::GetInfoUseCaseImpl) { bind<GetRocketImages>() }
    factoryOf(::GetRocketsUseCaseImpl) { bind<GetRocketsUseCase>() }
    factoryOf(::GetRocketDetailsUseCaseImpl) { bind<GetRocketDetailsUseCase>() }

    single<RocketImagesApi> { RetrofitInstance.api }

    single<ApolloInstance> { ApolloInstance }
}