package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.passwordScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.CodeAuthOutlineTextField
import uz.gita.mobilebankingcompose.utils.button.AppButtonApelsin
import uz.gita.mobilebankingcompose.utils.passwordVisualTransformation

@Parcelize
class PasswordScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("PasswordScreen") {

    @Composable
    override fun Content() {
        PasswordScreenContent()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordScreenContent() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background_A))
    ) {
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }

        var passwordStatus by remember { mutableStateOf(false) }
        var confirmStatus by remember { mutableStateOf(false) }
        var focusPassword by remember { mutableStateOf(false) }
        var focusConfirm by remember { mutableStateOf(false) }
        var passwordVisible by remember { mutableStateOf(false) }
        var confirmVisible by remember { mutableStateOf(false) }

        var required_1 by remember { mutableStateOf(false) }
        var required_2 by remember { mutableStateOf(false) }
        var required_3 by remember { mutableStateOf(false) }
        var required_4 by remember { mutableStateOf(false) }
        var required_5 by remember { mutableStateOf(false) }
        var required_6 by remember { mutableStateOf(false) }


        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_ios),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.padding(top = 32.dp))
            Text(
                text = stringResource(R.string.registraration_a),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6.copy(fontSize = 32.sp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CodeAuthOutlineTextField(
                value = password,
                style = MaterialTheme.typography.subtitle2,
                resource = R.string.password,
                onChangeValue = {

                    Timber.d("passwordStatus $passwordStatus")
                    if (it.length > 20) return@CodeAuthOutlineTextField
                    required_1 = it.length in 9..20
                    required_4 = it.isNotEmpty()
                    for (i in it.indices) {
                        var arr = it.toCharArray()
                        required_2 = arr[i] in 'a'..'z'
                        required_5 = arr[i] in 'A'..'Z'
//                        (arr[i] == '+'|| '-' || '!').also { required_6 = it }
                        required_3 = arr[i] in '0'..'9'

                    }

                    passwordStatus = it.length >= 8
                    password = it
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .onFocusChanged { focusPassword = it.isFocused },
                colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Transparent,
                    backgroundColor = when (focusPassword) {
                        false -> colorResource(id = R.color.backText)
                        else -> colorResource(id = R.color.visibleTextColor)
                    }
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.password),
                        color = colorResource(id = R.color.invisibleTextColor)
                    )
                },
                singleLine = true,
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description =
                        if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                appKeyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                visualTransformation = passwordVisualTransformation(passwordVisible = passwordVisible),
                modifierOutline = Modifier
                    .fillMaxWidth(),
                isError = false,
                errorText = "",
            )

            Spacer(modifier = Modifier.padding(8.dp))

            CodeAuthOutlineTextField(
                value = passwordConfirm,
                style = MaterialTheme.typography.subtitle2,
                resource = R.string.confirm_passord,
                onChangeValue = {
                    Timber.d("passwordStatus $confirmStatus")
                    if (it.length > 20) return@CodeAuthOutlineTextField
                    confirmStatus = it.length >= 8
                    passwordConfirm = it
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .onFocusChanged { focusConfirm = it.isFocused },
                colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Transparent,
                    backgroundColor = when (focusConfirm) {
                        false -> colorResource(id = R.color.backText)
                        else -> colorResource(id = R.color.visibleTextColor)
                    }
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.password),
                        color = colorResource(id = R.color.invisibleTextColor)
                    )
                },
                singleLine = true,
                trailingIcon = {
                    val image = if (confirmVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description =
                        if (confirmVisible) "Hide password" else "Show password"

                    IconButton(onClick = { confirmVisible = !confirmVisible }) {
                        Icon(imageVector = image, description)

                    }
                },
                appKeyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                visualTransformation = passwordVisualTransformation(passwordVisible = confirmVisible),
                modifierOutline = Modifier
                    .fillMaxWidth(),
                isError = false,
                errorText = "",
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_icon),
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = stringResource(R.string.create_required),
                    style = MaterialTheme.typography.h6.copy(fontSize = 16.sp)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = when (required_1) {
                        false -> painterResource(id = R.drawable.ic_disable)
                        else -> painterResource(id = R.drawable.ic_true)
                    },
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = stringResource(R.string.required_one),
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 14.sp,
                        color = colorResource(
                            id = R.color.text_grey
                        )
                    )
                )
            }


            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = when (required_2) {
                        false -> painterResource(id = R.drawable.ic_disable)
                        else -> painterResource(id = R.drawable.ic_true)
                    },
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = stringResource(R.string.required_two),
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 14.sp, color = colorResource(
                            id = R.color.text_grey
                        )
                    )
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = when (required_3) {
                        false -> painterResource(id = R.drawable.ic_disable)
                        else -> painterResource(id = R.drawable.ic_true)
                    },
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = stringResource(R.string.required_three),
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 14.sp, color = colorResource(
                            id = R.color.text_grey
                        )
                    )
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = when (required_4) {
                        false -> painterResource(id = R.drawable.ic_disable)
                        else -> painterResource(id = R.drawable.ic_true)
                    },
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = stringResource(R.string.required_four),
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 14.sp, color = colorResource(
                            id = R.color.text_grey
                        )
                    )
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = when (required_5) {
                        false -> painterResource(id = R.drawable.ic_disable)
                        else -> painterResource(id = R.drawable.ic_true)
                    },
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = stringResource(R.string.required_five),
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 14.sp, color = colorResource(
                            id = R.color.text_grey
                        )
                    )
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = when (required_6) {
                        false -> painterResource(id = R.drawable.ic_disable)
                        else -> painterResource(id = R.drawable.ic_true)
                    },
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = stringResource(R.string.required_six),
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 14.sp, color = colorResource(
                            id = R.color.text_grey
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            AppButtonApelsin(
                text = stringResource(id = R.string.buttonText),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                onClick = {  },
                enabled = passwordStatus && confirmStatus && required_1 && required_2 && required_3 && required_4 || required_5 || required_6
            )

        }

    }

}

@Preview
@Composable
fun PasswordScreenContentPreview() {
    MobileBankingComposeTheme {
        PasswordScreenContent()
    }
}