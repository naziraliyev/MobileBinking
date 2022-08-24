    package uz.gita.mobilebankingcompose.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import uz.gita.mobilebankingcompose.R

@Composable
fun PinCode(
    modifier: Modifier,
    currentLength: Int,
    maxLength: Int
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {

        repeat(currentLength) {
            Image(
                painter = painterResource(id = R.drawable.ic_circle_pinned),
                contentDescription = ""
            )
        }
        repeat(maxLength - currentLength) {
            Image(
                painter = painterResource(id = R.drawable.ic_cicrle_unpinned),
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
fun PinCodePreview() {
    PinCode(modifier = Modifier, 2, 4)
}