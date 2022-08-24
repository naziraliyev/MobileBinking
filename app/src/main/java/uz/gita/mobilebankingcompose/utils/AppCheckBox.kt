package uz.gita.mobilebankingcompose.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.auth.privacy.PrivacyContract

@Composable
fun AppCheckBox(
    onChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier
) {

    var isChecked by remember { mutableStateOf(onChecked) }
Row(modifier = modifier.fillMaxWidth()) {

    Image(
        painter = when (isChecked) {
            false -> painterResource(id = R.drawable.ic_circle_unchecked)
            else -> painterResource(id = R.drawable.ic_cicle_checked)
        },
        contentDescription = "AppCheckBox",
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                onClick = {
                    isChecked = !isChecked
                    onCheckedChange.invoke(isChecked)
                }
            )
    )
    Text(
        modifier = modifier
            .padding(start = 10.dp)
            .weight(1f)
            .clickable(
                //interactionSource = remember { MutableInteractionSource() },
                //indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                onClick = {
                    isChecked = !isChecked
                    onCheckedChange.invoke(isChecked)
                }
            ),
        text = stringResource(id = R.string.text_policy_agreement),
        style = MaterialTheme.typography.body2.copy(
            color = colorResource(id = R.color.visibleTextColorPrivacy),
            fontSize = 14.sp,
            lineHeight = 18.sp
        )
    )
}
}

@Composable
fun AppCheckBoxApelsin(
    onChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    text:Int
) {

    var isChecked by remember { mutableStateOf(onChecked) }
    Row(modifier = modifier.fillMaxWidth()) {

        Image(
            painter = when (isChecked) {
                false -> painterResource(id = R.drawable.ic_circle_unchecked_a)
                else -> painterResource(id = R.drawable.ic_cicle_checked_a)
            },
            contentDescription = "AppCheckBox",
            modifier = modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                    onClick = {
                        isChecked = !isChecked
                        onCheckedChange.invoke(isChecked)
                    }
                )
        )
        Text(
            modifier = modifier
                .padding(start = 10.dp)
                .weight(1f)
                .clickable(
                    //interactionSource = remember { MutableInteractionSource() },
                    //indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                    onClick = {
                        isChecked = !isChecked
                        onCheckedChange.invoke(isChecked)
                    }
                ),
            text = stringResource(id = text),
            style = MaterialTheme.typography.body2.copy(
                color = colorResource(id = R.color.textColor),
                fontSize = 16.sp,
                lineHeight = 18.sp
            )
        )
    }
}