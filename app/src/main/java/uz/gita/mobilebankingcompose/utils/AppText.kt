package uz.gita.mobilebankingcompose.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import uz.gita.mobilebankingcompose.R

@Composable
fun AppText(
    text: String,
    modifier: Modifier,

) {
    Text(
        text, modifier = modifier
            .fillMaxWidth()


    )
}