package com.hompiler.whiteboard.ui.features.whiteboard

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import com.hompiler.whiteboard.models.Drawable
import com.hompiler.whiteboard.models.DrawingTool
import kotlin.math.abs


@Composable
fun WhiteboardCanvas(
    modifier: Modifier = Modifier,
    selectedTool: DrawingTool,
    selectedColor: Color,
    drawings: MutableList<Drawable>,
    addDrawable: (Offset) -> Unit,
    updateDrawable: (Offset) -> Unit,
) {

    var startOffset by remember { mutableStateOf<Offset>(Offset(-1.0f, -1.0f)) }
    var endOffset by remember { mutableStateOf<Offset>(Offset(-1.0f, -1.0f)) }

    val pointerModifier = modifier
        .pointerInput(selectedTool, selectedColor) {
            forEachGesture {

                awaitPointerEventScope {

                    // ACTION_DOWN
                    val down = awaitFirstDown()
                    startOffset = down.position

                    addDrawable(startOffset)
                    do {



                        // ACTION_MOVE
                        val event: PointerEvent = awaitPointerEvent()
                        endOffset = event.changes[0].position
                        updateDrawable(endOffset)

                        event.changes.forEach { pointerInputChange: PointerInputChange ->
                            pointerInputChange.consumePositionChange()
                        }
                    } while (event.changes.any { it.pressed })

                    // ACTION_UP


                }
            }
        }

    Canvas(
        modifier = pointerModifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()

    ) {

        drawLine(
            color = Color.Transparent,
            start = startOffset,
            end = endOffset,
        )

        for (drawing in drawings)
            drawing.draw(this)

    }
}
