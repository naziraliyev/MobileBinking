package uz.gita.mobilebankingcompose.utils.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebankingcompose.R

@Composable
fun AppButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit,
    enabled:Boolean
) {
    Button(
        onClick = {
            onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (enabled) {
                true -> colorResource(id = R.color.visibleColor)
                else -> colorResource(id = R.color.invisibleColor)
            }
        ),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .requiredHeight(48.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = when (enabled) {
                true -> colorResource(id = R.color.visibleTextColor)
                else -> colorResource(id = R.color.invisibleTextColor)

            }
        )

    }
}
@Composable
fun AppButtonApelsin(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit,
    enabled:Boolean
) {
    Button(
        onClick = {
            onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (enabled) {
                true -> colorResource(id = R.color.buttonVisibleColor)
                else -> colorResource(id = R.color.buttonInVisibleColorApelsin)
            }
        ),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .requiredHeight(56.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = when (enabled) {
                true -> colorResource(id = R.color.textVisibleColor)
                else -> colorResource(id = R.color.textInVisibleColor)

            }
        )

    }
}