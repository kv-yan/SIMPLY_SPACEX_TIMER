package simply.homework.spacextimer.spacexinfo.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import simply.homework.spacextimer.spacexinfo.presentation.contract.InfoContract
import simply.homework.spacextimer.spacexinfo.presentation.model.ScreensUIState
import simply.homework.spacextimer.spacexinfo.presentation.screen.details.SpaceXDetailsScreen
import simply.homework.spacextimer.spacexinfo.presentation.screen.info.SpaceXInfoScreen
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: SpaceXInfoMVIViewModel,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is InfoContract.Effect.NavigateToDetails -> {
                    navController.navigate(ScreensUIState.SPACEX_DETAILS_SCREEN.route)
                }

                InfoContract.Effect.ReturnBackFromDetailScreen -> {
                    navController.popBackStack()
                }
            }
        }
    }


    NavHost(
        navController = navController,
        startDestination = ScreensUIState.SPACEX_INFO_SCREEN.route
    ) {

        // SPACEX Info list screen
        composable(ScreensUIState.SPACEX_INFO_SCREEN.route) {
            SpaceXInfoScreen(viewModel = viewModel, modifier = modifier)
        }

        // Details screen
        composable(ScreensUIState.SPACEX_DETAILS_SCREEN.route) {
            SpaceXDetailsScreen(modifier = modifier.fillMaxSize(), viewModel = viewModel)
        }
    }
}

