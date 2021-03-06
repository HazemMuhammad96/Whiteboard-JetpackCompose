package com.hompiler.whiteboard.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

class Pencil(
    var points: MutableState<MutableList<Offset>> = mutableStateOf(mutableListOf()),
    override var color: Color = Color.Black
) : Drawable {

    override var draw: DrawScope.() -> Unit = {
        drawPath(
            path = Path().apply {
                points.value.forEachIndexed { i, point ->
                    if (i == 0) {
                        moveTo(point.x, point.y)
                    } else {
                        lineTo(point.x, point.y)
                    }
                }
            },
            color = color,
            style = Stroke(4.dp.toPx())
        )
    }

}

