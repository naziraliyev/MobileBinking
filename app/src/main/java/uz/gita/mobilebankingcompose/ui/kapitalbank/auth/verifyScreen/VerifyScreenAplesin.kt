package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.verifyScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import uz.gita.mobilebanking.utils.convertLongToTime
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.ui.auth.verify.AuthTextButton
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.CodeOutlineTextFieldApelsin
import uz.gita.mobilebankingcompose.utils.button.AppButtonApelsin

@Parcelize
class VerifyScreenApelsin(
    override val screenKey: String = uniqueScreenKey, val data: SignUpData
) :
    ComposeScreen("VerifyScreen") {
    @Composable
    override fun Content() {
        val viewModel: VerifyViewModel = viewModel<VerifyScreenViewModelImpl>()
        viewModel.initData(data)
        VerifyScreenApelsinContent(viewModel.state.collectAsState(), viewModel::onEvent)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerifyScreenApelsinContent(
    state: State<VerifyContract.State>,
    event: (VerifyContract.Event) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        var codeStatus by remember { mutableStateOf(false) }
        var code by remember { mutableStateOf(TextFieldValue("")) }
        var codeNumber by remember { mutableStateOf("") }

        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_back_ios),
                    contentDescription = "", modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = { event(VerifyContract.Event.OnBackPressed) })
                )
                Text(
                    text = "",
                    color = colorResource(id = R.color.textColor),
                    modifier = Modifier.weight(1f)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(
                    text = stringResource(R.string.approve),
                    style = MaterialTheme.typography.h6
                )
                Spacer(
                    modifier = Modifier
                        .requiredHeight(32.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = stringResource(
                        id = R.string.enter_code,
//                    state.value.phoneNumber
                    ),
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(
                    modifier = Modifier
                        .requiredHeight(16.dp)
                        .fillMaxWidth()
                )
                CodeOutlineTextFieldApelsin(
                    value = code,
                    singleLine = true,
                    style = MaterialTheme.typography.subtitle2,
                    resource = R.string.enter_phone_code,
                    onChangeValue = {
                        if (codeStatus) event(VerifyContract.Event.FilledApelsin) else VerifyContract.Event.UnFilled
                        if (it.text.length > 5) return@CodeOutlineTextFieldApelsin
                        codeStatus = it.text.length == 5

                        if (it.text.isEmpty()) {
                            code = TextFieldValue("", selection = TextRange(1))
                        } else if (it.text.length < 9) {
                            code = it
                            Timber.d("code $code")
                            Timber.d("code $codeStatus")
                        }
                        codeNumber = code.text
                    },
                    modifier = Modifier.padding(top = 16.dp),
                    placeholder = {
                        Text(text = "0 0 0 0 0")
                    },

                    modifierOutline = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { codeStatus = it.isFocused },
                    colorsApp = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.brentColor),
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = when (codeStatus) {
                            false -> colorResource(id = R.color.backText)
                            else -> colorResource(id = R.color.visibleTextColor)
                        }
                    ),
                    appKeyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = { codeNumber(it) },
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),

                    )
                Spacer(
                    modifier = Modifier
                        .requiredHeight(16.dp)
                        .fillMaxWidth()
                )
                if (!state.value.progressStatus) {
                    Box(modifier = Modifier.padding(top = 8.dp)) {
                        when (state.value.errorStatus) {
                            false -> {
                                if (state.value.timeStatus) {
                                    Text(
                                        text = stringResource(id = R.string.send_again_in)+  convertLongToTime(state.value.timer),
                                        style = MaterialTheme.typography.subtitle2.copy(
                                            lineHeight = 22.sp,
                                        )
                                    )
                                } else {
                                    AuthTextButton(
                                        onClick = {  },
                                        text = R.string.text_resend
                                    )
                                }
                            }
                            else -> {
                                Text(
                                    text = state.value.message,
                                    style = MaterialTheme.typography.subtitle2.copy(
//                                        color = colorResource(id = R.color.text_red),
                                        lineHeight = 22.sp,
                                    )
                                )
                            }

                        }
                    }
                }
//                Text(
//                    text = stringResource(
//                        id = R.string.send_again_in,
////                    state.value.phoneNumber
//                    ),
//                    style = MaterialTheme.typography.subtitle2
//                )
                Spacer(modifier = Modifier.weight(1f))

                AppButtonApelsin(
                    text = stringResource(R.string.buttonText),
                    modifier = Modifier,
                    onClick = { event(VerifyContract.Event.Verified(codeNumber)) },
                    enabled = codeStatus
                )
            }


        }


    }
}


const val maskCode = "0 0 0 0 0"
fun codeNumber(text: AnnotatedString): TransformedText {
    val trimmed =
        if (text.text.length >= 5) text.text.substring(0..4) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 0) append(" ")
            if (i == 1) append(" ")
            if (i == 2) append(" ")
            if (i == 3) append(" ")

        }
        pushStyle(SpanStyle(color = Color.LightGray))
        append(maskCode.takeLast(maskCode.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
//
            if (offset <= 0) return offset
            if (offset <= 1) return offset + 1
            if (offset <= 2) return offset + 2
            if (offset <= 3) return offset + 3
            return 9
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 0) return offset
            if (offset <= 1) return offset - 1
            if (offset <= 2) return offset - 2
            if (offset <= 3) return offset - 3
            return 5
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun VerifyScreenApelsinContentPreview() {
    MobileBankingComposeTheme {
        VerifyScreenApelsinContent(state = mutableStateOf(VerifyContract.State()), {})
    }
}