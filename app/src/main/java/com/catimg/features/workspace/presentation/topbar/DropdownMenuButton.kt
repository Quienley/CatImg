package com.catimg.features.workspace.presentation.topbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun DropdownMenuButton(text: String,
                       imageVector: ImageVector,
                       action: () -> Unit) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val isPressed = remember { mutableStateOf(false) }

    LaunchedEffect(isPressed.value) {
        if (isPressed.value) {
            delay(750)
            isPressed.value = false
        }
    }

    DropdownMenuItem(
        modifier = Modifier.width(250.dp).height(40.dp),
        leadingIcon = { Icon(
            modifier = Modifier.size(17.dp).offset(x = 4.dp),
            imageVector = imageVector,
            tint = onBackgroundColor,
            contentDescription = "$text action"
        ) },
        text = {
            Text(text = text,
                color = onBackgroundColor,
                fontSize = 18.sp)
        },
        onClick = {
            if (!isPressed.value) {
                isPressed.value = true
                action()
            }
        }
    )
}