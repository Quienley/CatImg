package com.catimg.features.mainmenu.presentation.buttons

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.catimg.R
import com.catimg.features.mainmenu.viewmodel.MainViewModel

@Composable
fun LoadPhotoButton(viewModel: MainViewModel,
                    navController: NavHostController) {
    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.reset()
            viewModel.downloadToCache(uri, context)
            navController.navigate("image_display")
        }
    }

    MenuButton(
        label = "Import photo",
        iconPainter = painterResource(R.drawable.button_ic_load_photo),
        onClickAction = { galleryLauncher.launch("image/*")}
    )
}