package com.catimg.features

import android.os.Bundle
import com.catimg.core.ui.theme.CatImgTheme
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.catimg.features.mainmenu.viewmodel.MainViewModel
import com.catimg.navigation.NavigationApp

class MainActivity : ComponentActivity() {
    private val photoViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatImgTheme(photoViewModel.darkTheme.value) {
                NavigationApp(photoViewModel)
            }
        }
    }
}