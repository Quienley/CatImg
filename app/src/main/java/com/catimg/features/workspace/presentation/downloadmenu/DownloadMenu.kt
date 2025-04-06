package com.catimg.features.workspace.presentation.downloadmenu

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catimg.features.workspace.presentation.settings.SettingsButton
import com.catimg.features.mainmenu.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val LINE_HEIGHT = 1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadMenu(viewModel: MainViewModel) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background

    val snackbarHostState = remember { SnackbarHostState() }
    val message = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val saveCurrentItem = remember { mutableStateOf(true) }
    val isSaved = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val labelFontSize = 21.sp

    BackHandler(enabled = true) {
        viewModel.decrementScreenStatus()
        viewModel.navController.popBackStack()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.11f),
                title = {
                    Text(
                        modifier = Modifier.offset(y = 3.dp),
                        text = "Download options",
                        fontSize = 28.sp,
                        color = onBackgroundColor
                    )
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.decrementScreenStatus()
                        viewModel.navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = onBackgroundColor
                        )
                    }
                })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Write a name to your file (Optional)",
                fontSize = labelFontSize,
                color = onBackgroundColor
            )
            TextField(
                value = message.value,
                onValueChange = { message.value = it },
                textStyle = TextStyle(fontSize = 18.sp),
                leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Checked") },
                trailingIcon = { Icon(Icons.Filled.Info, contentDescription = "Info") },
                placeholder = { Text("my_cool_image") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )

            SettingsButton(
                text = "Generate name",
                modifier = Modifier.fillMaxWidth()) {
                    message.value = "image_${System.currentTimeMillis()}"
                }

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(LINE_HEIGHT.dp)
                    .background(onBackgroundColor)
                    .padding(vertical = 5.dp)
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(25.dp))

            Text(
                text = "Parameters of downloading",
                fontSize = labelFontSize,
                color = onBackgroundColor
            )
            Column(
                modifier = Modifier.selectableGroup(),
                verticalArrangement = Arrangement.spacedBy(13.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    RadioButton(
                        modifier = Modifier.size(24.dp),
                        selected = saveCurrentItem.value,
                        onClick = { saveCurrentItem.value = true }
                    )
                    Text(
                        text = "Save current item in history",
                        color = onBackgroundColor,
                        fontSize = 18.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    RadioButton(
                        modifier = Modifier.size(24.dp),
                        selected = !saveCurrentItem.value,
                        onClick = { saveCurrentItem.value = false }
                    )
                    Text(
                        text = "Save last item in history",
                        color = onBackgroundColor,
                        fontSize = 18.sp
                    )
                }
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(LINE_HEIGHT.dp)
                    .background(onBackgroundColor)
                    .padding(vertical = 5.dp)
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(25.dp))

            SettingsButton(
                text = "Save image",
                modifier = Modifier.fillMaxWidth()
            ) {
                if (!isSaved.value) {
                    val name = if (message.value == "") {
                        "image_${System.currentTimeMillis()}"
                    } else message.value

                    isSaved.value =
                        viewModel.saveBitmapToGallery(
                            context,
                            name,
                            saveCurrentItem.value
                        )
                    scope.launch {
                        while (!isSaved.value) {
                            delay(100)
                        }
                        snackbarHostState.showSnackbar(
                            actionLabel = "Saving",
                            message = "Downloaded ${
                                if (saveCurrentItem.value)
                                    "current" else
                                    "the last"
                            } " + "image from history\n" +
                            "with name: \"${name}\"",
                            duration = SnackbarDuration.Short
                        )
                        isSaved.value = false
                    }
                }
            }
        }
    }
}