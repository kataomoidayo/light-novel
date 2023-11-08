package com.putu.lightnovel.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Favorite : Screen("favorite")

    object About : Screen("about")

    object DetailLightNovel: Screen("home/{lightNovelId}") {
        fun createRoute(lightNovelId: Int) = "home/$lightNovelId"
    }
}
