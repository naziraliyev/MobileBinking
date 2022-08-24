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
fun CirclePinCode(
    modifier: Modifier,
    currentLength: Int,
    maxLength: Int
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {

        repeat(currentLength) {
            Image(
                painter = painterResource(id = R.drawable.ic_circle_pinned_a),
                contentDescription = ""
            )
        }
        repeat(maxLength - currentLength) {
            Image(
                painter = painterResource(id = R.drawable.ic_cicrle_unpinned_a),
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
fun CirclePinCodePreview() {
    CirclePinCode(modifier =Modifier , currentLength =1 , maxLength = 4)
}