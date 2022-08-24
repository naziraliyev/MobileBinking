package uz.gita.mobilebankingcompose.ui.auth.verify


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import uz.gita.mobilebanking.utils.convertLongToTime
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.AppKeyboard
import uz.gita.mobilebankingcompose.utils.OTPTextFields
import uz.gita.mobilebankingcompose.utils.button.AppButton

@Parcelize
class VerifyScreen(
    override val screenKey: String = uniqueScreenKey,
    val data: SignUpData,
) :
    ComposeScreen("VerifyScreen") {

    @Composable
    override fun Content() {
        val viewModel: VerifyScreenViewModel = viewModel<VerifyScreenViewModelImpl>()
        viewModel.initData(data)
        VerifyScreenContent(viewModel.state.collectAsState(), viewModel::onEvent)
    }
}

@Composable
fun VerifyScreenContent(
    state: State<VerifyContract.State>,
    event: (VerifyContract.Event) -> Unit
) {
    var buttonVisible by remember { mutableStateOf(false) }
    var code by remember { mutableStateOf("") }
    val length by remember { mutableStateOf(6) }
//    Surface(modifier = Modifier.fillMaxSize()) {
//        ConstraintLayout {
//            val (title, textSentNumber, boxCode, textTimer, progress, buttonAccept, keyboard) = createRefs()
//            var code by remember { mutableStateOf("") }
//            val length by remember { mutableStateOf(6) }
//
//            Row(modifier = Modifier
//                .padding(start = 16.dp, end = 16.dp)
//                .constrainAs(title) {
//                    top.linkTo(parent.top, margin = 16.dp)
//                    linkTo(start = parent.start, end = parent.end)
//                }) {
//                BackButton(onClick = { event(VerifyContract.Event.OnBackPressed) })
//                Image(
//                    painterResource(id = R.drawable.ic_back),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
//                        .clickable(
//                            interactionSource = remember { MutableInteractionSource() },
//                            indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
//                            onClick = { event(VerifyContract.Event.OnBackPressed) }
//                        )
//                )
//
//                Text(
//                    text = stringResource(R.string.confirm),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(end = 56.dp)
//                        .padding(4.dp, 8.dp),
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.Center,
//                    color = colorResource(id = R.color.color_title)
//                )
//
//
//            }
//
//            /*Text(
//                text = stringResource(id = R.string.text_accept),
//                modifier =
//                Modifier
//                    .constrainAs(textTitle) {
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        top.linkTo(parent.top, margin = 16.dp)
//                    },
//                fontSize = 20.sp,
//                color = colorResource(id = R.color.color_verification_title),
//                style = MaterialTheme.typography.subtitle1*/
////            )
//
////            if (state.value.phoneNumber.isNotEmpty()) {
//            Text(
//                text = stringResource(
//                    id = R.string.send,
//                    state.value.phoneNumber
//                ),
//                modifier = Modifier
//                    .constrainAs(textSentNumber) {
//                        linkTo(parent.top, parent.bottom, bias = 0.1625f)
//                        linkTo(parent.start, parent.end)
//                    },
//                style = MaterialTheme.typography.subtitle2.copy(
//                    color = colorResource(id = R.color.text_light_black),
//                    textAlign = TextAlign.Center
//                )
//            )
////            }
//
//            OTPTextFields(
//                length = length,
//                code = code,
//                modifier = Modifier
//                    .padding(32.dp)
//                    .constrainAs(boxCode) {
//                        linkTo(parent.top, parent.bottom, bias = 0.29375f)
//                        linkTo(parent.start, parent.end)
//                    },
//                filled = { event(VerifyContract.Event.Filled) },
//                unFilled = { event(VerifyContract.Event.UnFilled) },
//                errorStatus = state.value.errorStatus
//            )
//
//            if (!state.value.progressStatus) {
//                Box(modifier = Modifier
//                    .constrainAs(textTimer) {
//                        linkTo(parent.start, parent.end)
//                        linkTo(parent.top, parent.bottom, bias = 0.4f)
//                    }) {
//                    when (state.value.errorStatus) {
//                        false -> {
//                            if (state.value.timerStatus) {
//                                Text(
//                                    text = convertLongToTime(state.value.timer),
//                                    style = MaterialTheme.typography.subtitle2.copy(
//                                        lineHeight = 22.sp,
//                                    )
//                                )
//                            } else {
//                                AuthTextButton(
//                                    onClick = { event(VerifyContract.Event.Retry) },
//                                    text = R.string.text_resend
//                                )
//                            }
//                        }
//                        else -> {
//                            Text(
//                                text = state.value.message,
//                                style = MaterialTheme.typography.subtitle2.copy(
//                                    color = colorResource(id = R.color.text_red),
//                                    lineHeight = 22.sp,
//                                )
//                            )
//                        }
//
//                    }
//                }
//            }
//
//            if (state.value.progressStatus) {
//                CircularProgressIndicator(modifier = Modifier.constrainAs(progress) {
//                    linkTo(parent.start, parent.end)
//                    top.linkTo(boxCode.bottom)
//                    bottom.linkTo(buttonAccept.top)
//                })
//            }
//
//            AppButton(
//                onClick = { event(VerifyContract.Event.OnRegister(code = code)) },
//                modifier = Modifier
//                    .padding(start = 16.dp, end = 16.dp)
//                    .constrainAs(buttonAccept) {
//                        linkTo(parent.start, parent.end)
//                        bottom.linkTo(keyboard.top, margin = 16.dp)
//                    },
//                enabled = state.value.acceptButtonStatus,
//                text = stringResource(id = R.string.registration)
//            )
//
//            AppKeyboard(
//                numberOnclick = { digit ->
//                    if (state.value.isKeyboardEnabled) {
//                        if (code.length >= length) return@AppKeyboard
//                        code += digit
//                    }
//                },
//                clearOnClick = {
//                    event(VerifyContract.Event.ActionKeyboard)
//                    if (state.value.isKeyboardEnabled) code = ""
//                },
//                backSpaceOnClick = {
//                    event(VerifyContract.Event.ActionKeyboard)
//                    if (state.value.isKeyboardEnabled) {
//                        if (code.isNotEmpty()) code =
//                            code.substring(0, code.length - 1)
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxHeight(0.38125f)
//                    .constrainAs(keyboard) {
//                        bottom.linkTo(parent.bottom)
//                    }
//            )
//        }
//    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(56.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(id = R.drawable.ic_back),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                            onClick = { event(VerifyContract.Event.OnBackPressed) }
                        )
                )

                Text(
                    text = stringResource(R.string.confirm),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 56.dp)
                        .padding(4.dp, 8.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.color_title)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(
                        id = R.string.text_verification_sent_number,
                        state.value.phoneNumber
                    ),
                    modifier = Modifier
                        .padding(top = 64.dp, bottom = 8.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.color_text)
                )


                OTPTextFields(
                    modifier = Modifier.padding(start = 40.dp, end = 40.dp),
                    length = length,
                    code = code,
                    filled = {
                        Timber.d("TTT Filled")
                        event(VerifyContract.Event.Filled)
                    },
                    unFilled = {
                        Timber.d("TTT UnFilled")
                        event(VerifyContract.Event.UnFilled)
                    },
                    errorStatus = state.value.errorStatus
                )


                if (!state.value.progressStatus) {
                    Box(modifier = Modifier.padding(top = 8.dp)) {
                        when (state.value.errorStatus) {
                            false -> {
                                if (state.value.timerStatus) {
                                    Text(
                                        text = convertLongToTime(state.value.timer),
                                        style = MaterialTheme.typography.subtitle2.copy(
                                            lineHeight = 22.sp,
                                        )
                                    )
                                } else {
                                    AuthTextButton(
                                        onClick = { event(VerifyContract.Event.Retry) },
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

                if (state.value.progressStatus) {
                    CircularProgressIndicator(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.weight(1f))
                AppButton(

                    text = when (buttonVisible) {
                        false -> stringResource(id = R.string.registration)
                        else -> stringResource(id = R.string.confirm)
                    },
                    modifier = Modifier.padding(
                        top = 100.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                    enabled = state.value.acceptButtonStatus,
                    onClick = {
                        event(VerifyContract.Event.OnRegister(code = code))
                        Timber.d("code $code")

                    }

                )


                AppKeyboard(
                    numberOnclick = { digit ->
                        if (state.value.isKeyboardEnabled) {
                            if (code.length >= length) return@AppKeyboard
                            code += digit
                        }
                    },
                    clearOnClick = {
                        event(VerifyContract.Event.ActionKeyboard)
                        state.value.progressStatus = false
                        if (state.value.isKeyboardEnabled) code = ""
                    },
                    backSpaceOnClick = {

                        state.value.progressStatus = false
                        event(VerifyContract.Event.ActionKeyboard)
                        if (state.value.isKeyboardEnabled) {
                            if (code.isNotEmpty()) code =
                                code.substring(0, code.length - 1)
                        }
                    },
                    modifier = Modifier
                )
            }
        }
    }

}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun VerifyScreenContentPreview() {
    MobileBankingComposeTheme() {
        VerifyScreenContent(
            state = mutableStateOf(VerifyContract.State()),
            event = {},
        )
    }
}