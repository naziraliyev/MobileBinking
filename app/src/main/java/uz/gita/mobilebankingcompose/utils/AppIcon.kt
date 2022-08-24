package uz.gita.mobilebanking.utils.customui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.gita.mobilebankingcompose.R

@Composable
fun AppIcon(
    modifier: Modifier,

) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_group), contentDescription = "")
        Image(painter = painterResource(id = R.drawable.ic_gita_bank_black), contentDescription = "")
    }

}
