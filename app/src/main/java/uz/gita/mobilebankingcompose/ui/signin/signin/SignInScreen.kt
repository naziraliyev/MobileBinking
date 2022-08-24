@file:OptIn(ExperimentalComposeUiApi::class)

package uz.gita.mobilebankingcompose.ui.signin.signin

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.auth.login.mobileNumberFilter
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.AppText
import uz.gita.mobilebankingcompose.utils.PhoneOutlineTextField
import uz.gita.mobilebankingcompose.utils.button.AppButton

@Parcelize
class SignInScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("SignInScreen") {

    @Composable
    override fun Content() {
        val viewModel: SignInViewModel = viewModel<SignInViewModelImpl>()
        SignInScreenContent(viewModel.state.collectAsState(), viewModel::onEvent)
    }
}

@Composable
fun SignInScreenContent(state: State<SignInContract.State>, event: (SignInContract.Event) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize())
    {
        var phoneNumberStatus by rememberSaveable { mutableStateOf(state.value.isFilledPhoneNumber) }
        var passwordStatus by rememberSaveable { mutableStateOf(state.value.isFilledPassword) }

        var focusStateOfPassword by remember { mutableStateOf(false) }
        var focusStateOfPhone by remember { mutableStateOf(false) }

        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        var password by rememberSaveable { mutableStateOf("") }
        var phoneNumber by rememberSaveable { mutableStateOf("") }

        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Sign In", modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.subtitle1.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            )


            var phone by remember { mutableStateOf(TextFieldValue("+998")) }
            PhoneOutlineTextField(
                value = phone,
                style = MaterialTheme.typography.subtitle2,
                resource = R.string.phoneNumber,
                onChangeValue = {
                    if (it.text.length > 13) return@PhoneOutlineTextField
                    phoneNumberStatus = it.text.length == 13
                    if (it.text.length < 5) {
                        phone = TextFieldValue("+998", selection = TextRange(6))
                    } else if (!phone.text.startsWith("+998")) {
                        phone = TextFieldValue("+998", selection = TextRange(6))
                    } else if (it.text.length < 18) {
                        phone = it
                    }
                },
                modifier = Modifier.padding(top = 16.dp),
                placeholder = {
                    Text(text = "+998 00-000-00-00")
                },
                modifierOutline = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusStateOfPhone = it.isFocused },
                colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Transparent,
                    backgroundColor = when (focusStateOfPhone) {
                        false -> colorResource(id = R.color.backText)
                        else -> colorResource(id = R.color.visibleTextColor)
                    }
                ),
                appKeyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = { mobileNumberFilter(it) }

            )

//            AppText(
//                text = stringResource(R.string.phoneNumber),
//                modifier = Modifier.padding(top = 104.dp, start = 8.dp, bottom = 8.dp)
//            )
//
//            Row(modifier = Modifier.fillMaxWidth()) {
//
//                OutlinedTextField(
//                    value = phoneNumber,
//                    singleLine = true,
//                    placeholder = { Text("+998 00-000-00-00") },
//                    onValueChange = { phone ->
//                        if (phone.length>13) return@OutlinedTextField
//                        phoneNumberStatus = phone.length == 13
//
//                        phoneNumberStatus = phone.startsWith("+998") &&
//                                phone.substring(1).matches("^[0-9]*$".toRegex()) &&
//                                phone.length == 13
//                        phoneNumber = phone
//                    },
//                    /*visualTransformation = { mobileNumberFilter(it) },*/
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .onFocusChanged { focusStateOfPhone = it.isFocused },
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        focusedBorderColor = Color.Green,
//                        unfocusedBorderColor = Color.Transparent,
//                        backgroundColor = when (focusStateOfPhone) {
//                            false -> colorResource(id = R.color.backText)
//                            else -> colorResource(id = R.color.visibleTextColor)
//                        }
//                    ),
//                    shape = RoundedCornerShape(12.dp),
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Phone,
//                        imeAction = ImeAction.Next
//                    ),
//
//
//                    )
//
//            }
            var errorPhoneVisibility = state.value.errorPhone
            AnimatedVisibility(
                visible = errorPhoneVisibility,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = stringResource(id = R.string.phone_not_found),
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.errorColor),
                    modifier = Modifier.padding(bottom = 8.dp, top = 4.dp)
                )
            }

            AppText(
                text = stringResource(R.string.password),
                modifier = Modifier.padding(top = 16.dp, start = 8.dp, bottom = 8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        passwordStatus = it.length >= 6
                        password = it
                    },
                    singleLine = true,
                    placeholder = { Text("Password", color = colorResource(id = R.color.invisibleTextColor)) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ), keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusStateOfPassword = it.isFocused },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = when (focusStateOfPassword) {
                            false -> colorResource(id = R.color.backText)
                            else -> colorResource(id = R.color.visibleTextColor)
                        }
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
            }
            var errorPasswordVisibility = state.value.errorPassword
            AnimatedVisibility(
                visible = errorPasswordVisibility,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = stringResource(id = R.string.password_not_found),
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.errorColor),
                    modifier = Modifier.padding(bottom = 8.dp, top = 4.dp)
                )
            }

            Text(
                text = stringResource(R.string.forget_password),
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp, bottom = 8.dp)
                    .clickable {
                        event(SignInContract.Event.ForgetPassword)
                    },
                color = colorResource(id = R.color.forgot_password),
                style = MaterialTheme.typography.subtitle2.copy(
                    fontWeight = W500,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily.SansSerif
                )

            )

            var buttonVisible by remember { mutableStateOf(false) }
            buttonVisible = phoneNumberStatus && passwordStatus
            Spacer(modifier = Modifier.weight(1f))
            AppButton(
                text = stringResource(id = R.string.enter),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                enabled = buttonVisible,
                onClick = {
                    if (buttonVisible) {
                        event(SignInContract.Event.Registration(phoneNumber, password))
                    }
                })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.have_account),
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontWeight = W500,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = colorResource(id = R.color.blackless)
                    )
                )
            Spacer(modifier = Modifier.requiredWidth(10.dp))
                Text(
                    text = stringResource(R.string.sign_up),
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontWeight = W500,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = colorResource(id = R.color.visibleColor)),
                    modifier = Modifier
                        .clickable(onClick = { event(SignInContract.Event.RegistrationButton) })
                )
            }
        }
    }

}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun SignInScreenContentPreview() {
    MobileBankingComposeTheme() {
        SignInScreenContent(state = mutableStateOf(SignInContract.State()), event = {})
    }
}
