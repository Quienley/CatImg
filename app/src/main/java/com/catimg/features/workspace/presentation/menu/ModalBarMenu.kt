package com.catimg.features.workspace.presentation.menu

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catimg.R
import com.catimg.features.mainmenu.viewmodel.MainViewModel
import kotlinx.coroutines.launch

private const val LINE_HEIGHT = 1
private const val PADDING = 24
private const val SPACING = 14

@Composable
fun ModalBarMenu(drawerState: DrawerState,
                 mainViewModel: MainViewModel
) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val surfaceColor = MaterialTheme.colorScheme.surface
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(SPACING.dp),
        modifier = Modifier.fillMaxSize()
            .padding(vertical = PADDING.dp, horizontal = (PADDING / 2).dp)) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {

            Text(text = "App Menu", fontSize = 28.sp)
            IconButton(onClick = { coroutineScope.launch { drawerState.close() } } ) {
                Icon(imageVector = Icons.Filled.Close,
                    contentDescription = "Close menu",
                    tint = onBackgroundColor)
            }
        }

        Box(Modifier.fillMaxWidth()
            .height(LINE_HEIGHT.dp)
            .background(onBackgroundColor))

        Row(modifier = Modifier.fillMaxWidth()
            .background(surfaceColor)
            .clickable { mainViewModel.inverseAppTheme() }
            .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            AnimatedContent(
                targetState = mainViewModel.darkTheme.value,
                label = "Theme Icon",
                transitionSpec = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(500)
                    ) togetherWith
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(500)
                    )
                }
            ) { isDarkTheme ->
                val themeIcon = if (isDarkTheme) {
                    R.drawable.modal_menu_dark_theme
                } else {
                    R.drawable.modal_menu_light_theme
                }

                Icon(imageVector = ImageVector.vectorResource(themeIcon),
                    contentDescription = "Change Theme",
                    tint = onBackgroundColor,
                    modifier = Modifier.size(20.dp))
            }
            Text(text = "Change Theme", fontSize = 18.sp)
        }
    }
}