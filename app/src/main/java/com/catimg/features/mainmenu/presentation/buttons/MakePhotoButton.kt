package com.catimg.features.mainmenu.presentation.buttons

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.catimg.R
import com.catimg.features.mainmenu.viewmodel.MainViewModel

@Composable
fun MakePhotoButton(viewModel: MainViewModel,
                    navController: NavHostController) {
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                imageUri.value?.let {
                    viewModel.reset()
                    viewModel.downloadToCache(it, context)
                    navController.navigate("image_display")
                }
            }
        }
    )

    MenuButton(
        label = stringResource(R.string.make_photo),
        iconPainter = painterResource(R.drawable.button_ic_make_photo),
        onClickAction = {
            val photoFile = viewModel.createImageFile(context)
            val uri = FileProvider.getUriForFile(
                context,
                "com.catimg.temp",
                photoFile
            )

            imageUri.value = uri
            takePictureLauncher.launch(uri)
        }
    )
}
