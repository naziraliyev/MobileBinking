package uz.gita.mobilebankingcompose.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun AppKeyboard(
    numberOnclick: (digit: Int) -> Unit,
    clearOnClick: () -> Unit,
    backSpaceOnClick: () -> Unit,
    modifier: Modifier
) {

    Column(modifier = modifier.background(color = colorResource(id = R.color.otp_background))) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumberButton(onClick = numberOnclick, number = 1, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
            NumberButton(onClick = numberOnclick, number = 2, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
            NumberButton(onClick = numberOnclick, number = 3, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumberButton(onClick = numberOnclick, number = 4, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
            NumberButton(onClick = numberOnclick, number = 5, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
            NumberButton(onClick = numberOnclick, number = 6, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumberButton(onClick = numberOnclick, number = 7, modifier = Modifier
                .weight(1f)
                .fillMaxSize()

                .padding(8.dp))
            NumberButton(onClick = numberOnclick, number = 8, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
            NumberButton(onClick = numberOnclick, number = 9, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ActionButton(onClick = clearOnClick, icon = R.drawable.ic_clear, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
            NumberButton(onClick = numberOnclick, number = 0, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
            ActionButton(onClick = backSpaceOnClick,icon = R.drawable.ic_backspace, modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp))
        }
    }
}

@Composable
fun NumberButton(
    onClick: (digit: Int) -> Unit,
    number: Int,
    modifier: Modifier
) {
    TextButton(
        onClick = { onClick.invoke(number) },
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.background_otp_number_button)),
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "$number",
            style = TextStyle(
                color = colorResource(id = R.color.black),
                fontSize = 36.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W300,
                lineHeight = 42.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun ActionButton(
    onClick:()->Unit,
    modifier: Modifier,
    icon:Int = 0
){
    TextButton(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.background_otp_action_button)
        ),
        onClick = { onClick.invoke() },
        modifier = modifier,
        shape = RoundedCornerShape(4.dp)
    ) {
        Image(
            painter = painterResource(id =icon),
            contentDescription = "",
        )
    }
}

@Preview
@Composable
fun AppKeyboardPreview(){
    MobileBankingComposeTheme() {
        AppKeyboard({},{},{}, modifier = Modifier,)
    }
}