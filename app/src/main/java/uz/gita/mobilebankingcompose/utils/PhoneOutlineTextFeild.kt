package uz.gita.mobilebankingcompose.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import uz.gita.mobilebankingcompose.R

@Composable
fun PhoneOutlineTextField(
    value: TextFieldValue,
    style: TextStyle,
    resource: Int,
    onChangeValue: (TextFieldValue) -> Unit,
    modifier: Modifier,
    modifierOutline: Modifier,
    placeholder: @Composable () -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    colorsApp: TextFieldColors = TextFieldDefaults.textFieldColors(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions(onDone = { }),
    appKeyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false,
    errorText: String = "",
    icon :@Composable (()->Unit)? = null
)   {
    Column(modifier = modifier) {
        var phone = value
        Text(
            text = stringResource(id = resource),
            style = style,
            modifier = Modifier.padding( bottom = 8.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {

            OutlinedTextField(
                value = phone,
                leadingIcon = icon,
                onValueChange = onChangeValue,
                singleLine = singleLine,
                placeholder = placeholder,
                visualTransformation = visualTransformation,
                keyboardOptions = appKeyboardOptions,
                keyboardActions = keyboardActions,
                trailingIcon = trailingIcon,
                modifier =modifierOutline,
                colors = colorsApp,
                shape = RoundedCornerShape(12.dp)
            )
        }


        var errorPhoneVisibility = isError
        AnimatedVisibility(
            visible = errorPhoneVisibility,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = errorText,
                style = style.copy(
                    color = colorResource(id = R.color.red_text)
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}