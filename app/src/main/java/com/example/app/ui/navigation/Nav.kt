package com.example.app.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.components.FloatingPlusButton
import com.example.app.ui.components.Header
import com.example.app.ui.screens.AddEditPerfumeScreen
import com.example.app.ui.screens.ListScreen
import com.example.app.ui.viewModel.PefumeViewModel
import com.example.app.ui.viewModel.events.PerfumeEvent



@Composable
fun MyNavHost() {
    val navController = rememberNavController()
    val viewModel: PefumeViewModel = hiltViewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route


    Scaffold(
        topBar = {
            when (currentRoute) {
                Routes.HOME -> Header("Your perfumes", "Admire your perfumes")
                Routes.FAVORITES -> Header("Favorites", "Your most loved perfumes")
                Routes.ADD -> Header("Add new perfume", "Refresh your collection")
                Routes.EDIT -> Header("Edit perfume", "Update your collection")
                else -> Header("Your perfumes", "Admire your flowers")
            }
        },
        floatingActionButton = {
            if (currentRoute == Routes.HOME || currentRoute == Routes.FAVORITES) {
                FloatingPlusButton(
                    onClick = { navController.navigate(Routes.ADD) }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }

    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(Routes.HOME) {
                val state by viewModel.state.collectAsStateWithLifecycle()
                Column() {
                    ListScreen(
                        state = state,
                        onDeletePerfume = { perfume -> viewModel.onEvent(PerfumeEvent.DeletePerfume(perfume)) },
                        onEditPerfume = { id -> navController.navigate("edit/$id") },
                        onToggleFavorite = { perfume -> viewModel.onEvent(PerfumeEvent.ToggleFavorite(perfume)) }
                    )
                }
            }

            composable(Routes.FAVORITES) {
                val state by viewModel.state.collectAsStateWithLifecycle()
                val favorites = state.filter { it.favourite }
                Column {
                    ListScreen(
                        state = favorites,
                        onDeletePerfume = { perfume -> viewModel.onEvent(PerfumeEvent.DeletePerfume(perfume)) },
                        onEditPerfume = { id -> navController.navigate("edit/$id") },
                        onToggleFavorite = { perfume -> viewModel.onEvent(PerfumeEvent.ToggleFavorite(perfume)) }
                    )
                }
            }

            composable(Routes.ADD) {
                AddEditPerfumeScreen(
                    text = "Add new flower",
                    subText = "Refreshen your flower collection",
                    buttonText = "ADD",
                    onSubmit = { perfume ->
                        viewModel.onEvent(PerfumeEvent.AddPerfume(perfume))
                        navController.popBackStack()
                    }
                )
            }

            composable("edit/{id}") { entry ->
                val id = entry.arguments?.getString("id")?.toIntOrNull() ?: 0
                val perfume = viewModel.getPerfumeById(id)
                AddEditPerfumeScreen(
                    text = if (perfume != null) "Edit ${perfume.name}" else "Error retrieving perfume :(",
                    subText = "Update your perfume collection",
                    buttonText = "EDIT",
                    existingPerfume = perfume,
                    onSubmit = { updatedPerfume ->
                        viewModel.onEvent(PerfumeEvent.UpdatePerfume(updatedPerfume))
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
