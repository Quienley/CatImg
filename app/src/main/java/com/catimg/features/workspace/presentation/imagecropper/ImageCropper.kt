package com.catimg.features.workspace.presentation.imagecropper

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.catimg.features.workspace.presentation.settings.SettingsButton
import com.catimg.features.mainmenu.viewmodel.MainViewModel
import com.canhub.cropper.CropImageView
import com.catimg.R

@Composable
fun ImageCropper(viewModel: MainViewModel) {
    val backgroundColor = MaterialTheme.colorScheme.background
    var cropImageView: CropImageView? = null

    BackHandler(enabled = true) {
        viewModel.decrementScreenStatus()
        viewModel.navController.popBackStack()
    }

    Column(modifier = Modifier.fillMaxSize().background(backgroundColor)) {
        Column(modifier = Modifier.weight(0.85f).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            AndroidView(
                modifier = Modifier,
                factory = { context ->
                    CropImageView(context).apply {
                        setImageBitmap(viewModel.photoBitmap.value)
                        cropImageView = this
                    }
                }
            )
        }

        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().weight(0.15f).background(backgroundColor)) {
            SettingsButton(text = stringResource(R.string.image_cropper_cancel)) {
                viewModel.refresh()
                viewModel.decrementScreenStatus()
                viewModel.navController.popBackStack()
            }

            SettingsButton(text = stringResource(R.string.image_cropper_apply)) {
                cropImageView?.let {
                    viewModel.photoBitmap.value = it.getCroppedImage()
                    viewModel.addToCache()
                    viewModel.decrementScreenStatus()
                    viewModel.navController.popBackStack()
                }
            }
        }
    }
}