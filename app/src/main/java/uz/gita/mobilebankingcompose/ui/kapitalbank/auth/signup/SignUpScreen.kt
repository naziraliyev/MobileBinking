@file:OptIn(ExperimentalComposeUiApi::class)

package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.PhoneOutlineTextField
import uz.gita.mobilebankingcompose.utils.button.AppButtonApelsin

@Parcelize
class SignUpScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("SignUpScreen") {

    @Composable
    override fun Content() {

        val viewModel: SignUpViewModel = viewModel<SignUpViewModelImpl>()
        SignUpScreenContent(viewModel.state.collectAsState(), viewModel::dispatchersEvent)
    }
}

@Composable
fun SignUpScreenContent(state: State<SignUpContract.State>, event: (SignUpContract.Event) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        var phoneStatus by remember { mutableStateOf(false) }
        var phoneNumber by remember { mutableStateOf("") }

        var phone by remember { mutableStateOf(TextFieldValue("+998")) }
        var focusPhone by remember { mutableStateOf(false) }

        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_backspace),
                    contentDescription = "", modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Connection with bank",
                    color = colorResource(id = R.color.textColor),
                    modifier = Modifier.clickable {

                    })
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(text = "Welcome", style = MaterialTheme.typography.h6)
                Spacer(
                    modifier = Modifier
                        .requiredHeight(16.dp)
                        .fillMaxWidth()
                )
                PhoneOutlineTextField(
                    value = phone,
                    singleLine = true,
                    style = MaterialTheme.typography.subtitle2,
                    resource = R.string.enter_phone_number,
                    onChangeValue = {
                        Timber.d("phoneStatus $phoneStatus")
                        if (it.text.length > 13) return@PhoneOutlineTextField

                        phoneStatus = it.text.length == 13
                        Timber.d("length ${it.text.length}")

                        if (it.text.length < 5) {
                            phone = TextFieldValue("+998", selection = TextRange(5))
                        } else if (!phone.text.startsWith("+998")) {
                            phone = TextFieldValue("+998", selection = TextRange(5))
                        } else if (it.text.length < 16) {
                            phone = it
                            Timber.d("phoneStatus $phone")
                        }
                        phoneNumber = phone.text.substring(4)
                        Timber.d("phoneStatus1 $phoneNumber")
                    },
                    modifier = Modifier.padding(top = 16.dp),
                    placeholder = {
                        Text(text = "+998 00-000-00-00")
                    },

                    modifierOutline = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusPhone = it.isFocused },
                    colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.brentColor),
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = when (focusPhone) {
                            false -> colorResource(id = R.color.backText)
                            else -> colorResource(id = R.color.visibleTextColor)
                        }
                    ),
                    appKeyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = { mobileNumber(it) },
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = ""
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(1f))

                AppButtonApelsin(
                    text = stringResource(R.string.buttonText),
                    modifier = Modifier,
                    onClick = {
                        event(SignUpContract.Event.RegistrationApelsin(
                        firstName = "No Name",
                        lastName = "No Name",
                        phoneNumber = phoneNumber,
                        password = "123456789"
                    )) },
                    enabled = phoneStatus
                )
            }


        }


    }
}

const val masknumber = "+998 | 000000000"

fun mobileNumber(text: AnnotatedString): TransformedText {
    val trimmed =
        if (text.text.length >= 13) text.text.substring(0..12) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 3) append(" | ")

        }
        pushStyle(SpanStyle(color = Color.LightGray))
        append(masknumber.takeLast(masknumber.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
//
            if (offset <= 3) return offset
            if (offset <= 5) return offset + 1
            if (offset <= 8) return offset + 2
            if (offset <= 10) return offset + 3
            return 16
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
@Preview
@Composable
fun SignUpScreenContentPreview() {
    MobileBankingComposeTheme {
        SignUpScreenContent(state = mutableStateOf(SignUpContract.State()), {})
    }
}