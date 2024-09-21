package com.techgv.disasterhelper.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.techgv.disasterhelper.R
import com.techgv.disasterhelper.data.entity.Message
import com.techgv.disasterhelper.navigation.TopLevelDestination
import kotlinx.coroutines.launch

/**
 * This composable function is used to be called from [AppNavigation].
 */
@Composable
fun HomeRoute(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeScreenUiState by remember { homeViewModel.response }.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = homeScreenUiState,
        navController = navController
    )
}

/**
 * This composable function is used to display the content of home screen that is preview-able,
 * which means it does not take any dependency that would be difficult to provide from compose preview.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    navController: NavController,
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Disaster Helper", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                Spacer(Modifier.size(10.dp))
                NavigationDrawerItem(
                    label = { Text(text = "Home") },
                    selected = true,
                    onClick = {  }
                )
                Spacer(Modifier.size(10.dp))
                NavigationDrawerItem(
                    label = { Text(text = "Community") },
                    selected = true,
                    onClick = { navController.navigate(TopLevelDestination.CommunityScreen.route) }
                )
                Spacer(Modifier.size(10.dp))
                NavigationDrawerItem(
                    label = { Text(text = "Help Line") },
                    selected = true,
                    onClick = { navController.navigate(TopLevelDestination.HelpLineScreen.route) }
                )
            }
        },
        gesturesEnabled = true,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Disaster Helper") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            modifier = Modifier.fillMaxSize(),
            content = { paddingValues: PaddingValues ->
                HomeScreenContent(
                    modifier = Modifier.padding(paddingValues)
                )
            }
        )
    }

}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Welcome to Disaster Helper", fontSize = 24.sp)
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeScreenUiState.Success(msg = Message(text = stringResource(id = R.string.welcome_message))),
        navController = rememberNavController()
    )
}
