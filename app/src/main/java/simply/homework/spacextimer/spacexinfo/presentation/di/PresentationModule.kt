package simply.homework.spacextimer.spacexinfo.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

val presentationModule = module {
    viewModelOf(::SpaceXInfoMVIViewModel)
}