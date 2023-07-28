package com.compose.sample.onlinetictactoe.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.sample.onlinetictactoe.data.GameState

@Composable
fun TicTacToeField(
    modifier: Modifier = Modifier,
    state: GameState,
    playerXColor: Color = Color.Green,
    playerOColor: Color = Color.Red,
    onTapInField: (Int, Int) -> Unit
) {

    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectTapGestures {
                    val x = (3 * it.x.toInt() / size.width)
                    val y = (3 * it.y.toInt() / size.height)
                    onTapInField(x, y)
                }
            }
    ) {
        drawLine()

        state.field.forEachIndexed { y, _ ->
            state.field[y].forEachIndexed { x, player ->
                val offset = Offset(
                    x = x * size.width / 3f + size.width / 6f,
                    y = y * size.height / 3f + size.height / 6f,
                )
                if (player == 'X') {
                    drawX(playerXColor, offset)
                } else if (player == 'O') {
                    drawO(playerOColor, offset)
                }
            }
        }
    }
}

private fun DrawScope.drawO(
    color: Color,
    center: Offset,
    size: Size = Size(50.dp.toPx(), 50.dp.toPx()),
) {
    drawCircle(
        color = color,
        radius = size.width / 2f,
        center = center,
        style = Stroke(
            width = 3.dp.toPx()
        ),
    )
}

private fun DrawScope.drawX(
    color: Color,
    center: Offset,
    size: Size = Size(50.dp.toPx(), 50.dp.toPx())
) {
    drawTicLine(
        startX = (center.x - size.width / 2f),
        startY = (center.y - size.height / 2f),
        endX = (center.x + size.width / 2f),
        endY = (center.y + size.height / 2f),
        color = color
    )
    drawTicLine(
        startX = (center.x - size.width / 2f),
        startY = (center.y + size.height / 2f),
        endX = (center.x + size.width / 2f),
        endY = (center.y - size.height / 2f),
        color = color
    )
}

private fun DrawScope.drawLine(
) {
    drawTicLine(size.width / 3f, 0f, size.width / 3f, size.height)
    drawTicLine(size.width * 2 / 3f, 0f, size.width * 2 / 3f, size.height)

    drawTicLine(0f, size.height / 3f, size.width, size.height / 3f)
    drawTicLine(0f, size.height * 2 / 3f, size.width, size.height * 2 / 3f)
}

private fun DrawScope.drawTicLine(
    startX: Float,
    startY: Float,
    endX: Float,
    endY: Float,
    color: Color = Color.Black,
) {
    drawLine(
        color = color,
        start = Offset(startX, startY),
        end = Offset(endX, endY),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
}

@Preview(showBackground = true)
@Composable
fun TicTacToeFieldPreview() {
    TicTacToeField(
        modifier = Modifier.size(300.dp),
        state = GameState(
            field = arrayOf(
                arrayOf(null, 'X', 'O'),
                arrayOf(null, 'X', 'O'),
                arrayOf(null, 'X', null),
            )
        ),
        onTapInField = { _, _ -> }
    )
}
