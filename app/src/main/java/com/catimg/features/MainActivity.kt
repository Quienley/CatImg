package com.catimg.features

import android.os.Bundle
import com.catimg.core.ui.theme.CatImgTheme
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.catimg.features.mainmenu.viewmodel.MainViewModel
import com.catimg.features.mainmenu.viewmodel.MainViewModelFactory
import com.catimg.navigation.NavigationApp

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val context = LocalContext.current
            val factory = remember { MainViewModelFactory(context) }
            var mainViewModel: MainViewModel = viewModel(factory = factory)

            mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
            CatImgTheme(mainViewModel.darkTheme.value) {
                NavigationApp(mainViewModel)
            }
        }
    }
}
