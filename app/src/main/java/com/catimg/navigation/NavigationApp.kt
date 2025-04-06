package com.catimg.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catimg.features.workspace.presentation.downloadmenu.DownloadMenu
import com.catimg.features.mainmenu.presentation.MainMenuWindow
import com.catimg.features.workspace.presentation.WorkSpaceWindow
import com.catimg.features.workspace.presentation.imagecropper.ImageCropper
import com.catimg.features.mainmenu.viewmodel.MainViewModel

@Composable
fun NavigationApp(photoViewModel: MainViewModel) {
    val navController = rememberNavController()
    photoViewModel.navController = navController

    NavHost(navController = navController, startDestination = "main_menu") {
        composable(
            route = "main_menu",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End)
            }) {
            MainMenuWindow(
                viewModel = photoViewModel,
            )
        }
        composable(
            route = "image_display",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down)
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End)
            }) {
            WorkSpaceWindow(mainViewModel = photoViewModel,)
        }
        composable(
            route = "download_menu",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start)
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start)
            }) {
            DownloadMenu(viewModel = photoViewModel)
        }
        composable(
            route = "image_crop",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start)
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start)
            }) {
            ImageCropper(viewModel = photoViewModel)
        }
    }
}