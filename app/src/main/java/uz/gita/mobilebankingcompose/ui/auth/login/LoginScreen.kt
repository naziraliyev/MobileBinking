package uz.gita.mobilebankingcompose.ui.auth.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.AuthOutlineTextField
import uz.gita.mobilebankingcompose.utils.PhoneOutlineTextField
import uz.gita.mobilebankingcompose.utils.button.AppButton
import uz.gita.mobilebankingcompose.utils.passwordVisualTransformation

@Parcelize
class LoginScreen(override val screenKey: String = uniqueScreenKey) : ComposeScreen("LoginScreen") {

    @Composable
    override fun Content() {
        val viewModel: LoginScreenViewModel = viewModel<LoginScreenViewModelImpl>()

        LoginScreenContent(
            viewModel.state.collectAsState(),
            viewModel::onEvent,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreenContent(
    state: State<LoginContract.State>,
    event: (LoginContract.Event) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        var firstNameStatus by remember { mutableStateOf(false) }
        var lastNameStatus by remember { mutableStateOf(false) }
        var phoneNumberStatus by remember { mutableStateOf(false) }
        var passwordStatus by remember { mutableStateOf(false) }
        var firstname by remember { mutableStateOf("") }
        var lastname by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("+998") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        val focusManager = LocalFocusManager.current

        var phone by remember { mutableStateOf(TextFieldValue("+998")) }
        var focusStateOfPhone by remember { mutableStateOf(false) }
        var focusStateOfFirtName by remember { mutableStateOf(false) }
        var focusStateOfLastName by remember { mutableStateOf(false) }
        var focusStateOfPassword by remember { mutableStateOf(false) }


        var isErrorConfirm by rememberSaveable { mutableStateOf(false) }
        var errorText by rememberSaveable { mutableStateOf("") }

        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Sign Up",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 88.dp),

                verticalArrangement = Arrangement.Center
            ) {

                AuthOutlineTextField(
                    value = firstname,
                    style = MaterialTheme.typography.subtitle2,
                    resource = R.string.firstName,
                    onChangeValue = { name ->
                        Timber.d("firstNameStatus $firstNameStatus")
                        firstNameStatus = firstname.length > 3
                        firstname = name
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .onFocusChanged { focusStateOfFirtName = it.isFocused },
                    colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = when (focusStateOfFirtName) {
                            false -> colorResource(id = R.color.backText)
                            else -> colorResource(id = R.color.visibleTextColor)
                        }
                    ),
                    placeholder = {
                        Text(text = "First name",color = colorResource(id = R.color.invisibleTextColor))
                    },
                    modifierOutline = Modifier
                        .fillMaxWidth(),
                    appKeyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                )
//
                AuthOutlineTextField(
                    value = lastname,
                    style = MaterialTheme.typography.subtitle2,
                    resource = R.string.lastName,
                    onChangeValue = { name ->
                        Timber.d("lastNameStatus $lastNameStatus")
                        lastNameStatus = name.length >= 3
                        lastname = name
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .onFocusChanged { focusStateOfLastName = it.isFocused },
                    colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = when (focusStateOfLastName) {
                            false -> colorResource(id = R.color.backText)
                            else -> colorResource(id = R.color.visibleTextColor)
                        }
                    ),
                    placeholder = {
                        Text(text = "Last name",color = colorResource(id = R.color.invisibleTextColor))
                    },
                    modifierOutline = Modifier
                        .fillMaxWidth(),
                    appKeyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                )
                PhoneOutlineTextField(
                    value = phone,
                    style = MaterialTheme.typography.subtitle2,
                    resource = R.string.phoneNumber,
                    onChangeValue = {
                        Timber.d("phoneNumberStatus $phoneNumberStatus")
                        if (it.text.length > 13) return@PhoneOutlineTextField
                        Timber.d("phoneNumberStatus $phone")

                        phoneNumberStatus = it.text.length == 13
                        if (it.text.length < 5) {
                            phone = TextFieldValue("+998", selection = TextRange(5))
                        } else if (!phone.text.startsWith("+998")) {
                            phone = TextFieldValue("+998", selection = TextRange(5))
                        } else if (it.text.length < 18) {
                            phone = it
                            Timber.d("phoneNumberStatus $phone")
                        }
                        phoneNumber = phone.text.substring(4)
                        Timber.d("phoneNumberStatus $phoneNumber")
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
                    visualTransformation = { mobileNumberFilter(it) },
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                )
                AuthOutlineTextField(
                    value = password,
                    style = MaterialTheme.typography.subtitle2,
                    resource = R.string.password,
                    onChangeValue = {

                        Timber.d("passwordStatus $passwordStatus")
                        if (it.length > 12) return@AuthOutlineTextField
                        passwordStatus = it.length >= 6
                        password = it
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .onFocusChanged { focusStateOfPassword = it.isFocused },
                    colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = when (focusStateOfPassword) {
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
                    isError = isErrorConfirm,
                    errorText = errorText,

                    )
//
            }


            Spacer(modifier = Modifier.weight(1f))
            val buttonVisible =
                firstNameStatus && lastNameStatus && phoneNumberStatus && passwordStatus
            Timber.d("buttonVisible $firstNameStatus  && $lastNameStatus &&  $phoneNumberStatus && $passwordStatus}")
            AppButton(
                text = stringResource(id = R.string.registration),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                enabled = buttonVisible,
                onClick = {
//                    if (!isErrorPassword) isErrorPassword = true
//                    if (!passwordStatus) {
//                        isErrorPassword = true
//                        errorText = "Password has more 6 symbols"
//                    }
//                    if (newPassword == confirmPassword) {
//                        event(ChangePasswordContract.Event.ChangePassword(password = confirmPassword))
//                    } else {
//                        isErrorConfirm = true
//                        errorText = "Passwords not equals"
//                    }
                    event(
                        LoginContract.Event.Registration(
                            firstname,
                            lastname,
                            phone.text,
                            password
                        )
                    )
                })

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 32.dp),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    modifier = Modifier.padding(end = 8.dp),
//                    text = stringResource(id = R.string.text_signin_title),
//                    style = MaterialTheme.typography.body2
//                )
//                AuthTextButton(
//                    onClick = { if (state.value.isSignInButtonEnabled) event(SignUpContract.Event.OnBackPressed) },
//                    text = R.string.text_signin
//                )
//            }

        }
    }
}


const val mask = "+998 | 00 000 00 00"

fun mobileNumberFilter(text: AnnotatedString): TransformedText {
    val trimmed =
        if (text.text.length >= 13) text.text.substring(0..12) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i==3) append(" | ")
            if (i == 5 || i == 8 || i == 10) {
                append(" ")
            }
        }
        pushStyle(SpanStyle(color = Color.LightGray))
        append(mask.takeLast(mask.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 5) return offset + 1
            if (offset <= 8) return offset + 2
            if (offset <= 10) return offset + 3
            return 17
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 5) return offset - 1
            if (offset <= 8) return offset - 2
            if (offset <= 10) return offset - 3
            return 13
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}


@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun LoginScreenContentPreview() {
    MobileBankingComposeTheme {
        LoginScreenContent(
            state = mutableStateOf(LoginContract.State()),
            {},
        )
    }
}