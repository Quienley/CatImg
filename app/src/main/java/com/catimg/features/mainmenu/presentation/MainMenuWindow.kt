package com.catimg.features.mainmenu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.catimg.features.mainmenu.presentation.buttons.LoadPhotoButton
import com.catimg.features.mainmenu.presentation.buttons.MakePhotoButton
import com.catimg.features.mainmenu.viewmodel.MainViewModel

private const val CONTENT_SPACE = 18

@Composable
fun MainMenuWindow(viewModel: MainViewModel) {
    Box (contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),) {
        Column(verticalArrangement = Arrangement.spacedBy(CONTENT_SPACE.dp)) {
            MakePhotoButton(viewModel, viewModel.navController)
            LoadPhotoButton(viewModel, viewModel.navController)
        }
    }
}