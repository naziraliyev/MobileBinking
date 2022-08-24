@file:OptIn(ExperimentalComposeUiApi::class)

package uz.gita.mobilebankingcompose.ui.signin.recovery

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
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
import uz.gita.mobilebankingcompose.ui.auth.login.mobileNumberFilter
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.PhoneOutlineTextField
import uz.gita.mobilebankingcompose.utils.button.AppButton

@Parcelize
class RecoveryScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("RecoveryScreen") {

    @Composable
    override fun Content() {
        val viewModel: RecoveryViewModel = viewModel<RecoveryViewModelImpl>()
        RecoveryScreenContent(viewModel.state.collectAsState(), viewModel::onEvent)
    }
}

@Composable
fun RecoveryScreenContent(
    state: State<RecoveryContract.State>,
    event: (RecoveryContract.Event) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        var phoneNumberStatus by rememberSaveable { mutableStateOf(state.value.isFilledPhoneNumber) }

        var focusStateOfPhone by remember { mutableStateOf(false) }

        var phoneNumber by rememberSaveable { mutableStateOf("") }

        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.signIn), modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.subtitle1.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            )

            Text(
                text = stringResource(id = R.string.phone_content), modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp),
                style = MaterialTheme.typography.subtitle2.copy(
                    lineHeight = 22.sp,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )

            var phone by remember { mutableStateOf(TextFieldValue("+998")) }
            PhoneOutlineTextField(
                value = phone,
                style = MaterialTheme.typography.subtitle2,
                resource = R.string.phoneNumber,
                onChangeValue = {
                    Timber.d("phoneNumberStatus $phoneNumberStatus")
                    if (it.text.length > 13) return@PhoneOutlineTextField
                    Timber.d("phoneNumberStatus $phoneNumberStatus")

                        phoneNumberStatus = it.text.length==13
                    if (it.text.length <= 5) {
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
                    imeAction = ImeAction.Done
                ),
                visualTransformation = { mobileNumberFilter(it) },
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
            )

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
            Spacer(modifier = Modifier.weight(1f))

            AppButton(

                text = stringResource(id = R.string.send),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                enabled = phoneNumberStatus,
                onClick = {
                    event(
                        RecoveryContract.Event.Recovery(
                            phoneNumber = phoneNumber,
                            stateWindow = "forget"
                        )
                    )
                })

        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun RecoveryScreenContentPreview() {
    MobileBankingComposeTheme {
        RecoveryScreenContent(state = mutableStateOf(RecoveryContract.State()), event = {})
    }
}