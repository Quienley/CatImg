package com.catimg.features.workspace.presentation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.pointer.pointerInput
import androidx.lifecycle.viewmodel.compose.viewModel
import com.catimg.features.workspace.viewmodel.WorkSpaceViewModel
import com.catimg.features.workspace.presentation.topbar.TopBar
import com.catimg.features.workspace.presentation.menu.ModalBarMenu
import com.catimg.features.workspace.presentation.menu.WorkBundle
import com.catimg.features.workspace.presentation.menu.WorkImage
import com.catimg.features.mainmenu.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WorkSpaceWindow(mainViewModel: MainViewModel) {
    val primaryViewModel: WorkSpaceViewModel = viewModel()
    val scope = rememberCoroutineScope()

    val backgroundColor = MaterialTheme.colorScheme.background

    BackHandler(enabled = mainViewModel.screenState > 0) {
        mainViewModel.decrementScreenStatus()
        if (primaryViewModel.isAdjusting) {
            primaryViewModel.inverseAdjusting()
        }
        mainViewModel.refresh()
    }

    val snackbarHostState = remember { SnackbarHostState() }

    ModalNavigationDrawer(
        gesturesEnabled = false,
        drawerState = primaryViewModel.drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.background(backgroundColor)) {
                ModalBarMenu(primaryViewModel.drawerState, mainViewModel)
            }
        }) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
            TopBar(modifier = Modifier.fillMaxWidth(),
                viewModel = mainViewModel,
                primaryViewModel = primaryViewModel,
                openMenu = { scope.launch { primaryViewModel.openModalMenu() } })
        }) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)) {

                WorkImage(modifier = Modifier.weight(0.625f),
                    mainViewModel =  mainViewModel,
                    primaryViewModel = primaryViewModel)

                WorkBundle(modifier = Modifier.weight(0.3f),
                    mainViewModel =  mainViewModel,
                    primaryViewModel = primaryViewModel)

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.075f)
                    .clipToBounds()
                    .background(backgroundColor)
                    .pointerInput(Unit) { }
                )
            }
        }
    }
}