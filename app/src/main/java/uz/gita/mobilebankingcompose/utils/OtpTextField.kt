package uz.gita.mobilebankingcompose.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

@Composable
fun OTPTextFields(
    modifier: Modifier,
    length: Int,
    code: String,
    errorStatus: Boolean = false,
    filled: () -> Unit,
    unFilled: () -> Unit,
) {
    when (val currentPosition = code.length) {
        0 -> {
            unFilled.invoke()
            EmptyTextFields(
                modifier = modifier, length = length,
                errorStatus = errorStatus
            )
        }
        in 1 until length -> {
            unFilled.invoke()
            NotEmptyTextFields(
                modifier = modifier,
                currentPosition = currentPosition,
                length = length,
                code = code
            )
        }
        length -> {
            filled.invoke()
            FullTextFields(modifier = modifier, length = length, code = code, errorStatus = errorStatus)
        }
    }
}

@Composable
private fun EmptyTextFields(
    modifier: Modifier,
    length: Int,
    errorStatus: Boolean
) {
    Row(modifier = modifier) {
        OTPTextField(
            modifier = Modifier.weight(1f),
            backgroundColor = R.color.text_field_focused_indicator,
            errorStatus = errorStatus
        )
        repeat(length - 1) {
            OTPTextField(
                modifier = Modifier.weight(1f),
                backgroundColor = R.color.text_field_unfocused_indicator,
                errorStatus = errorStatus

            )
        }
    }
}

@Composable
private fun NotEmptyTextFields(
    modifier: Modifier,
    currentPosition: Int,
    length: Int,
    code: String
) {
    Row(modifier = modifier) {
        repeat(currentPosition) { position ->
            OTPTextField(
                modifier = Modifier.weight(1f),
                value = "${code[position]}",
                backgroundColor = R.color.text_field_unfocused_indicator
            )
        }
        OTPTextField(
            modifier = Modifier.weight(1f),
            backgroundColor = R.color.text_field_focused_indicator
        )
        repeat(length - currentPosition - 1) {
            OTPTextField(
                modifier = Modifier.weight(1f),
                backgroundColor = R.color.text_field_unfocused_indicator
            )
        }
    }
}

@Composable
private fun FullTextFields(
    modifier: Modifier,
    length: Int,
    code: String,
    errorStatus: Boolean = false
) {
    Row(modifier = modifier) {
        repeat(length) { position ->
            OTPTextField(
                modifier = Modifier.weight(1f),
                value = "${code[position]}",
                backgroundColor = R.color.text_field_unfocused_indicator,
                errorStatus = errorStatus
            )
        }
    }
}

@Composable
private fun OTPTextField(
    modifier: Modifier,
    value: String = "",
    backgroundColor: Int = R.color.text_field_unfocused_indicator,
    errorStatus: Boolean = false
) {
    OutlinedTextField(
        value = value,
        enabled = false,
        onValueChange = {},
        modifier = modifier.padding(4.dp),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = when (errorStatus) {
                false -> colorResource(id = R.color.text_field_background)
                else -> colorResource(id = R.color.errorColor)
            },
            unfocusedIndicatorColor = colorResource(id = backgroundColor),
        ),
        shape = RoundedCornerShape(8.dp),
        textStyle = MaterialTheme.typography.subtitle1.copy(
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.black)
        )
    )
}
//@Composable
//fun OtpTextField(
//    modifier: Modifier,
//    length: Int,
//    code: String
//) {
//    val currentPosition = code.length
//    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
//        repeat(currentPosition) { position ->
//            OutlinedTextField(
//                value = "${code[position]}",
//                onValueChange = {},
//                enabled = false,
//                modifier = Modifier
//                    .padding(4.dp)
//                    .weight(1f),
//                singleLine = true,
//                maxLines = 1,
//                shape = RoundedCornerShape(8.dp),
//                textStyle = TextStyle(
//                    fontFamily = FontFamily.SansSerif,
//                    fontWeight = FontWeight.W600,
//                    lineHeight = 32.sp,
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.Center,
//                    color = colorResource(id = R.color.black)
//                )
//            )
//        }
//        repeat(length-currentPosition) { position ->
//            OutlinedTextField(
//                value = "",
//                onValueChange = {},
//                enabled = false,
//                modifier = Modifier
//                    .padding(4.dp)
//                    .weight(1f),
//                singleLine = true,
//                maxLines = 1,
//                shape = RoundedCornerShape(8.dp),
//                textStyle = TextStyle(
//                    fontFamily = FontFamily.SansSerif,
//                    fontWeight = FontWeight.W600,
//                    lineHeight = 32.sp,
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.Center,
//                    color = colorResource(id = R.color.black)
//                )
//            )
//        }
//    }
//}


@Preview
@Composable
fun OTPTextFieldsPreview() {
    MobileBankingComposeTheme() {
        Surface {
            OTPTextFields(
                modifier = Modifier,
                length = 6,
                code = "123",
                filled = {},
                unFilled = {},
            )
        }
    }
}