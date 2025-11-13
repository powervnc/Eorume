package com.example.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavItem(

    val label: String,
    val icon: ImageVector,
    val route:String,
)

object Constants {
    val BottomNavItems = listOf(

        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = Routes.HOME
        ),

        BottomNavItem(
            label = "Favorites",
            icon = Icons.Filled.Favorite,
            route = Routes.FAVORITES
        ),

    )
}