package uz.gita.mobilebankingcompose.ui.auth.verify

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.gita.mobilebankingcompose.R

@Composable
fun AuthTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: Int
) {
    Text(
    modifier = modifier
    .clickable
    { onClick.invoke() },
    text = stringResource(id = text),
    style = MaterialTheme.typography.body1.copy(
    fontSize = 16.sp,
    color = colorResource(id = R.color.text_green),
    fontWeight = FontWeight.W500,
    lineHeight = 22.sp
    )
    )
}