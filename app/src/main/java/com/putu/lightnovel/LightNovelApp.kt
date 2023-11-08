package com.putu.lightnovel

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.putu.lightnovel.ui.component.BottomBar
import com.putu.lightnovel.ui.navigation.Screen
import com.putu.lightnovel.ui.screen.about.AboutScreen
import com.putu.lightnovel.ui.screen.detail.DetailScreen
import com.putu.lightnovel.ui.screen.favorite.FavoriteScreen
import com.putu.lightnovel.ui.screen.home.HomeScreen
import com.putu.lightnovel.ui.theme.LightNovelTheme

@Composable
fun LightNovelApp (
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailLightNovel.route) {
                BottomBar(navController)
            }
        },
        scaffoldState = scaffoldState,
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }

            composable(Screen.Favorite.route) {
                FavoriteScreen(navController)
            }

            composable(Screen.About.route) {
                AboutScreen()
            }

            composable(
                route = Screen.DetailLightNovel.route,
                arguments = listOf(navArgument("lightNovelId") { type = NavType.IntType }),
            ) {
                val lightNovelId = it.arguments?.getInt("lightNovelId") ?: 0
                DetailScreen(lightNovelId, navController, scaffoldState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LightNovelAppPreview() {
    LightNovelTheme {
        LightNovelApp()
    }
}