package uz.gita.mobilebankingcompose.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

@Composable
fun PinCodeKeyboard(
    modifier: Modifier,
    numberOnClick: (digit: Char) -> Unit,
    actionClearOnClick: () -> Unit,
    actionFingerPrintOnClick: () -> Unit = {},
    actionCheckOnClick: () -> Unit = {},
    isCheckButtonAvailable: Boolean = false,
    isFingerPrintAvailable: Boolean = false,
    isFilled: Boolean = false,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = 1, onClick = numberOnClick, modifier = Modifier.weight(1f))
            NumberButton(number = 2, onClick = numberOnClick, modifier = Modifier.weight(1f))
            NumberButton(number = 3, onClick = numberOnClick, modifier = Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = 4, onClick = numberOnClick, modifier = Modifier.weight(1f))
            NumberButton(number = 5, onClick = numberOnClick, modifier = Modifier.weight(1f))
            NumberButton(number = 6, onClick = numberOnClick, modifier = Modifier.weight(1f))
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = 7, onClick = numberOnClick, modifier = Modifier.weight(1f))
            NumberButton(number = 8, onClick = numberOnClick, modifier = Modifier.weight(1f))
            NumberButton(number = 9, onClick = numberOnClick, modifier = Modifier.weight(1f))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActionButton(
                drawable = R.drawable.ic_backspace,
                onClick = { actionClearOnClick.invoke() },
                modifier = Modifier
                    .weight(1f),
            )
            NumberButton(
                number = 0, onClick = numberOnClick, modifier =
                Modifier.weight(1f)
            )
            if (isFingerPrintAvailable) {
                ActionButton(
                    drawable = R.drawable.ic_fingerprint,
                    onClick = { actionFingerPrintOnClick.invoke() },
                    modifier = Modifier
                        .weight(1f),
                )
            } else if (isCheckButtonAvailable) {
                ActionButton(
                    modifier = Modifier.weight(1f),
                    drawable = when (isFilled) {
                        false -> R.drawable.ic_check_default
                        else -> R.drawable.ic_check_checked
                    }, onClick = { actionCheckOnClick.invoke() })
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun NumberButton(
    number: Int,
    onClick: (digit: Char) -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = { onClick(number.digitToChar()) },
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp),
        shape = RoundedCornerShape(80.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
    ) {
        Text(
            text = "$number",
            style = TextStyle(
                color = colorResource(id = R.color.black),
                fontSize = 32.sp,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily.SansSerif
            )
        )
    }
}

@Composable
private fun ActionButton(
    drawable: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(drawable),
        contentDescription = "",
        modifier = modifier
            .padding(4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                onClick = { onClick.invoke() }
            )
    )
}

@Preview
@Composable
fun pinCodeKeyboardPreview(){
    MobileBankingComposeTheme {
        PinCodeKeyboard(modifier =Modifier, numberOnClick ={}, actionClearOnClick = { /*TODO*/ })

    }
}