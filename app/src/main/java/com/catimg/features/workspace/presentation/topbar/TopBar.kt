package com.catimg.features.workspace.presentation.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.catimg.R
import com.catimg.features.workspace.viewmodel.MIN_SCALE
import com.catimg.features.workspace.viewmodel.WorkSpaceViewModel
import com.catimg.features.mainmenu.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier,
           viewModel: MainViewModel,
           primaryViewModel: WorkSpaceViewModel,
           openMenu: () -> Unit) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val blockedIcon = MaterialTheme.colorScheme.secondaryContainer
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }

    val isFirst = viewModel.isFirstPosition.value
    val isLast = viewModel.isLastPosition.value

    val animatedStepBackColor by animateColorAsState(
        targetValue = if (isFirst) blockedIcon
                    else onBackgroundColor,
        label = "Step back color state"
    )

    val animatedStepForwardColor by animateColorAsState(
        targetValue = if (isLast) blockedIcon
                    else onBackgroundColor,
        label = "Step back color state"
    )

    AnimatedVisibility(visible = !primaryViewModel.isAdjusting,
        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + fadeOut()) {

        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.11f)
                .then(modifier),
            title = {
                Text(text = "CatImg", modifier = Modifier.offset(y = 8.dp))
            },
            navigationIcon = {
                IconButton( onClick = { if (!primaryViewModel.isAdjusting) openMenu() } ) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = onBackgroundColor)
                }
            },
            actions = {
                IconButton(enabled = !isFirst,
                    onClick = { if (!primaryViewModel.isAdjusting) viewModel.back() } ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = animatedStepBackColor)
                }
                IconButton(enabled = !isLast,
                    onClick = { if (!primaryViewModel.isAdjusting) viewModel.forward() } ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Forward",
                        tint = animatedStepForwardColor)
                }
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Show options")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    containerColor = backgroundColor
                ) {
                    DropdownMenuButton(
                        text = "Share image",
                        imageVector = Icons.Default.Share,
                        action = { viewModel.shareImage(context) }
                    )
                    DropdownMenuButton(
                        text = "Export image",
                        imageVector = ImageVector.vectorResource(R.drawable.modal_menu_download),
                        action = {
                            viewModel.navController.navigate("download_menu")
                            viewModel.incrementScreenStatus()
                        }
                    )
                    Divider()
                    DropdownMenuButton(
                        text = "Flip horizontal",
                        imageVector = ImageVector.vectorResource(R.drawable.dropdown_flip_horizontal),
                        action = {
                            viewModel.flipImage(horizontal = true)
                        }
                    )
                    DropdownMenuButton(
                        text = "Flip vertical",
                        imageVector = ImageVector.vectorResource(R.drawable.dropdown_flip_vertical),
                        action = {
                            viewModel.flipImage(horizontal = false)
                        }
                    )
                    DropdownMenuButton(
                        text = "Rotate clockwise 90°",
                        imageVector = ImageVector.vectorResource(R.drawable.dropdown_turn_clockwise),
                        action = {
                            viewModel.rotateImage(90f)
                        }
                    )
                    DropdownMenuButton(
                        text = "Rotate anticlockwise 90°",
                        imageVector = ImageVector.vectorResource(R.drawable.dropdown_turn_anticlockwise),
                        action = {
                            viewModel.rotateImage(-90f)
                        }
                    )
                    DropdownMenuButton(
                        text = "Crop",
                        imageVector = ImageVector.vectorResource(R.drawable.dropdown_crop),
                        action = {
                            viewModel.incrementScreenStatus()
                            viewModel.navController.navigate("image_crop")
                        }
                    )
                    DropdownMenuButton(
                        text = "Zoom out",
                        imageVector = ImageVector.vectorResource(R.drawable.dropdown_zoom_out),
                        action = {
                            primaryViewModel.scale = MIN_SCALE
                            primaryViewModel.offset = Offset(0f, 0f)
                        }
                    )
                }
            }
        )
    }
}

@Composable
private fun Divider() = HorizontalDivider(
    color = MaterialTheme.colorScheme.onBackground,
    thickness = 1.dp,
    modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
)