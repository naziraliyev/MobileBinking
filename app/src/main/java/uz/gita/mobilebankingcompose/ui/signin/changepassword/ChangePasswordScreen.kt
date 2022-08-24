@file:OptIn(ExperimentalComposeUiApi::class)

package uz.gita.mobilebankingcompose.ui.signin.changepassword

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.AuthOutlineTextField
import uz.gita.mobilebankingcompose.utils.button.AppButton

@Parcelize
class ChangePasswordScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("ChangePassword") {

    @Composable
    override fun Content() {
        val viewModel: ChangePasswordScreenViewModel =
            viewModel<ChangePasswordScreenViewModelImpl>()
        ChangePasswordScreenContent(viewModel.state.collectAsState(), viewModel::onEvent)
    }
}

@Composable
fun ChangePasswordScreenContent(
    state: State<ChangePasswordContract.State>,
    event: (ChangePasswordContract.Event) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        var passwordStatus by rememberSaveable { mutableStateOf(state.value.isEquals) }


        var newPassword by rememberSaveable { mutableStateOf("") }
        var confirmPassword by rememberSaveable { mutableStateOf("") }
        var newPasswordVisible by rememberSaveable { mutableStateOf(false) }
        var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }
        var isErrorPassword by rememberSaveable { mutableStateOf(false) }
        var isErrorConfirm by rememberSaveable { mutableStateOf(false) }
        var errorText by rememberSaveable { mutableStateOf("") }


        var focusStateOfPassword by remember { mutableStateOf(false) }
        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.change_password),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = W600
                )
            )
            Spacer(modifier = Modifier.padding(top = 48.dp))
            AuthOutlineTextField(
                value = newPassword,
                onChangeValue = {
                    passwordStatus = it.length >= 6
                    newPassword = it
                },
                singleLine = true,
                placeholder = {
                    Text(
                        stringResource(id = R.string.new_password),
                        color = colorResource(id = R.color.invisibleTextColor)
                    )
                },
                appKeyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                trailingIcon = {
                    val image = if (newPasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description =
                        if (newPasswordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Transparent,
                    backgroundColor = when (focusStateOfPassword) {
                        false -> colorResource(id = R.color.backText)
                        else -> colorResource(id = R.color.visibleTextColor)
                    }
                ),
                isError = isErrorPassword,
                resource = R.string.new_password,
                style = MaterialTheme.typography.subtitle2,
                modifierOutline = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusStateOfPassword = it.isFocused },
            )

            AuthOutlineTextField(
                value = confirmPassword,
                onChangeValue = {
                    passwordStatus = it.length >= 6
                    confirmPassword = it
                },
                style = MaterialTheme.typography.subtitle2,
                singleLine = true,
                placeholder = {
                    Text(
                        stringResource(R.string.confirm_passord),
                        color = colorResource(id = R.color.invisibleTextColor)
                    )
                },
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() },
                ),
                appKeyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (confirmPasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description =
                        if (confirmPasswordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Transparent,
                    backgroundColor = when (focusStateOfPassword) {
                        false -> colorResource(id = R.color.backText)
                        else -> colorResource(id = R.color.visibleTextColor)
                    }
                ),
                isError = isErrorConfirm,
                resource = R.string.confirm_passord,
                errorText = errorText,
                modifierOutline = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusStateOfPassword = it.isFocused },
            )
            Spacer(modifier = Modifier.weight(1f))

            AppButton(
                text = stringResource(id = R.string.send),
                modifier = Modifier.padding(bottom = 16.dp),
                onClick = {
                    if (!isErrorPassword) isErrorPassword = true
                    if (!passwordStatus) {
                        isErrorPassword = true
                        errorText = "Password has more 6 symbols"
                    }
                    if (newPassword == confirmPassword) {
                        event(ChangePasswordContract.Event.ChangePassword(password = confirmPassword))
                    } else {
                        isErrorConfirm = true
                        errorText = "Passwords not equals"
                    }
                },
                enabled = true
            )

        }

    }
}

@Composable
fun PasswordOutlineFilled(
    value: String,
    style: TextStyle,
    resource: Int,
    onChangeValue: (String) -> Unit,
    modifier: Modifier,
    placeholder: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    singleLine: Boolean = false,
    colorsApp: TextFieldColors = TextFieldDefaults.textFieldColors(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions(onDone = { }),
    appKeyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false,
    errorText: String = ""
) {
    Column(modifier = modifier) {

        Text(
            text = stringResource(id = resource),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = value,
                onValueChange = onChangeValue,
                singleLine = singleLine,
                placeholder = placeholder,
                visualTransformation = visualTransformation,
                keyboardOptions = appKeyboardOptions,
                keyboardActions = keyboardActions,
                trailingIcon = trailingIcon,
                modifier = modifier
                    .fillMaxWidth(),
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
                style = MaterialTheme.typography.subtitle2.copy(
                    color = colorResource(id = R.color.red_text)
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun ChangePasswordScreenContentPreview() {
    MobileBankingComposeTheme {
        ChangePasswordScreenContent(
            state = mutableStateOf(ChangePasswordContract.State()),
            event = {})
    }
}