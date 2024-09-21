package com.techgv.disasterhelper.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.techgv.disasterhelper.ui.detail.DetailRoute
import com.techgv.disasterhelper.ui.home.HomeRoute
import com.techgv.disasterhelper.utility.Constants

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = TopLevelDestination.Home.route
    ) {
        composable(route = TopLevelDestination.Home.route) {
            HomeRoute(
                onNavigateClick = { source ->
                    navController.navigate(TopLevelDestination.Detail.withArgs(source))
                }
            )
        }

        composable(route = TopLevelDestination.Detail.route + "/{${Constants.SOURCE}}",
            arguments = listOf(
                navArgument(Constants.SOURCE) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val source = backStackEntry.arguments?.getString(Constants.SOURCE) ?: return@composable

            DetailRoute(
                source = source,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
