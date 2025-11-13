package com.example.app.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.components.FloatingPlusButton
import com.example.app.ui.components.Header
import com.example.app.ui.screens.AddEditPlantScreen
import com.example.app.ui.screens.ListScreen
import com.example.app.ui.viewModel.PlantViewModel
import com.example.app.ui.viewModel.events.PlantEvent



@Composable
fun MyNavHost() {
    val navController = rememberNavController()
    val viewModel: PlantViewModel = hiltViewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route


    Scaffold(
        topBar = {
            when (currentRoute) {
                Routes.HOME -> Header("Your flowers", "Admire your flowers")
                Routes.FAVORITES -> Header("Favorites", "Your most loved plants")
                Routes.ADD -> Header("Add new flower", "Refresh your collection")
                Routes.EDIT -> Header("Edit flower", "Update your collection")
                else -> Header("Your flowers", "Admire your flowers")
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
                Column {
                    ListScreen(
                        state = state,
                        onDeletePlant = { plant -> viewModel.onEvent(PlantEvent.DeletePlant(plant)) },
                        onEditPlant = { id -> navController.navigate("edit/$id") },
                        onToggleFavorite = { plant -> viewModel.onEvent(PlantEvent.ToggleFavorite(plant)) }
                    )
                }
            }

            composable(Routes.FAVORITES) {
                val state by viewModel.state.collectAsStateWithLifecycle()
                val favorites = state.filter { it.favourite }
                Column {
                    ListScreen(
                        state = favorites,
                        onDeletePlant = { plant -> viewModel.onEvent(PlantEvent.DeletePlant(plant)) },
                        onEditPlant = { id -> navController.navigate("edit/$id") },
                        onToggleFavorite = { plant -> viewModel.onEvent(PlantEvent.ToggleFavorite(plant)) }
                    )
                }
            }

            composable(Routes.ADD) {
                AddEditPlantScreen(
                    text = "Add new flower",
                    subText = "Refreshen your flower collection",
                    buttonText = "ADD",
                    onSubmit = { plant ->
                        viewModel.onEvent(PlantEvent.AddPlant(plant))
                        navController.popBackStack()
                    }
                )
            }

            composable("edit/{id}") { entry ->
                val id = entry.arguments?.getString("id")?.toIntOrNull() ?: 0
                val plant = viewModel.getPlantById(id)
                AddEditPlantScreen(
                    text = if (plant != null) "Edit ${plant.name}" else "Error retrieving plant :(",
                    subText = "Update your flower collection",
                    buttonText = "EDIT",
                    existingPlant = plant,
                    onSubmit = { updatedPlant ->
                        viewModel.onEvent(PlantEvent.UpdatePlant(updatedPlant))
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
