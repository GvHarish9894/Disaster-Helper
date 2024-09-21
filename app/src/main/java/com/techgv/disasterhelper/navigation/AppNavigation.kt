package com.techgv.disasterhelper.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.techgv.disasterhelper.ui.community.CommunityRoute
import com.techgv.disasterhelper.ui.detail.DetailRoute
import com.techgv.disasterhelper.ui.helpscreen.HelpScreenRoute
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
        startDestination = TopLevelDestination.HomeScreen.route
    ) {
        composable(route = TopLevelDestination.HomeScreen.route) {
            HomeRoute(navController)
        }

        composable(route = TopLevelDestination.CommunityScreen.route) {
            CommunityRoute(onBackClick = {
                navController.popBackStack()
            })
        }

        composable(route = TopLevelDestination.HelpLineScreen.route) {
            HelpScreenRoute(onBackClick = {
                navController.popBackStack()
            })
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
