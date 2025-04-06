package com.catimg.features.workspace.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.catimg.features.workspace.viewmodel.WorkSpaceViewModel
import com.catimg.domain.model.ToolFilter
import com.catimg.features.mainmenu.viewmodel.MainViewModel

const val HORIZONTAL_PADDING_SLIDER = 16
const val VERTICAL_PADDING_SLIDER = 10
const val COLUMN_SIZE_WITH_PARAMETERS = 190
const val COLUMN_SIZE_WITH_ONE_PARAMETER = 100
const val COLUMN_SIZE_WITHOUT_PARAMETERS = 40

@Composable
fun SettingsWindow(filter: ToolFilter,
                   mainViewModel: MainViewModel,
                   primaryViewModel: WorkSpaceViewModel
) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background

    val size = if (filter.parameters.isEmpty()) COLUMN_SIZE_WITHOUT_PARAMETERS
                else if (filter.parameters.size == 1) COLUMN_SIZE_WITH_ONE_PARAMETER
                else COLUMN_SIZE_WITH_PARAMETERS

    val context = LocalContext.current
    mainViewModel.applyFilter(context, filter.filter)

    LazyColumn(verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .size(size.dp)) {
        itemsIndexed(filter.parameters) { index, parameter ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(
                        horizontal = HORIZONTAL_PADDING_SLIDER.dp,
                        vertical = VERTICAL_PADDING_SLIDER.dp)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "${parameter.name}:", color = onBackgroundColor)
                    Text(
                        text = "${parameter.normalizeCurrentValueToUi()}",
                        color = onBackgroundColor)
                }
                Slider(
                    value = parameter.currentValue.floatValue,
                    onValueChange = {
                        newValue -> filter.setParameter(index, newValue)
                        parameter.currentValue.floatValue = newValue
                        mainViewModel.applyFilter(context, filter.filter)
                    },
                    valueRange = parameter.minValue..parameter.maxValue
                )
            }
        }
    }
    Row(horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()) {
        SettingsButton(text = "Cancel") {
            mainViewModel.refresh()
            mainViewModel.decrementScreenStatus()
            primaryViewModel.inverseAdjusting()
        }

        SettingsButton(text = "Commit") {
            mainViewModel.addToCache()
            mainViewModel.decrementScreenStatus()
            primaryViewModel.inverseAdjusting()
        }
    }
}