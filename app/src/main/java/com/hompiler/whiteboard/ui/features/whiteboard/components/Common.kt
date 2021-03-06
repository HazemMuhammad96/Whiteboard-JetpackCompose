package com.hompiler.whiteboard.ui.features.whiteboard

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CommonRowButton(
    commonModifier: Modifier = Modifier,
    onClick: () -> Unit,
    Icon: @Composable () -> Unit,
) {


    IconButton(
        modifier =
        commonModifier,
        onClick = onClick,

        ) {
        Icon()
    }
}


@Composable
fun CommonRow(
    rowModifier: Modifier = Modifier,
    ItemsComposable: @Composable () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = rowModifier
            .padding(24.dp, 0.dp)
            .alpha(0.9f)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            ItemsComposable()
        }

    }
}