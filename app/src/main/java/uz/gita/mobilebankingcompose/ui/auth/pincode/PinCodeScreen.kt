package uz.gita.mobilebankingcompose.ui.auth.pincode

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.PinCode
import uz.gita.mobilebankingcompose.utils.PinCodeKeyboard


@Parcelize
class PinCodeScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen(id = "PinCodeScreen") {

    @Composable
    override fun Content() {
        val viewModel: PinCodeViewModel = viewModel<PinCodeViewModelImpl>()
        PinCodeContent(state = viewModel.state.collectAsState(), event = viewModel::onEvent)
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PinCodeContent(
    state: State<PinCodeContract.State>,
    event: (PinCodeContract.Event) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout {
            val (skip, title, pinCodeRow, customKeyboard) = createRefs()
            val topGuideLineText = createGuidelineFromTop(0.146875f)
            val topGuideLinePinCode = createGuidelineFromTop(0.23125f)
            val topGuideLineKeyboard = createGuidelineFromTop(0.3375f)
            val inputValue = remember { mutableStateOf("") }
            val inputValue2 = remember { mutableStateOf("") }
            val maxLength = remember { mutableStateOf(4) }
            var bool = false
            val isFilled = maxLength.value == inputValue.value.length
            if (isFilled) event(PinCodeContract.Event.Filled)
            else event(PinCodeContract.Event.UnFilled)


//            Text(
//                text = stringResource(id = state.value.textSkip),
//                modifier = Modifier
//                    .clickable {
//                        if (isFilled) event(
//                            PinCodeContract.Event.Checked(
//                                inputValue.value
//                            )
//                        ) else event(PinCodeContract.Event.Skip)
//                    }
//                    .constrainAs(skip) {
//                        top.linkTo(parent.top, margin = 16.dp)
//                        end.linkTo(parent.end, margin = 16.dp)
//                    },
//                style = MaterialTheme.typography.body1
//            )

            Text(
                text = stringResource(id = R.string.text_pin_code_enter),
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topGuideLineText)
                },
                style = MaterialTheme.typography.body2.copy(
                    lineHeight = 22.sp
                )
            )

            PinCode(
                modifier = Modifier
                    .width(96.dp)
                    .constrainAs(pinCodeRow) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topGuideLinePinCode)
                    },
                currentLength = inputValue.value.length,
                maxLength = maxLength.value
            )

            PinCodeKeyboard(
                modifier = Modifier.constrainAs(customKeyboard) {
                    linkTo(start = parent.start, end = parent.end)
                    linkTo(top = topGuideLineKeyboard, bottom = parent.bottom)
                },
                numberOnClick = { digit ->
                    if (inputValue.value.length > maxLength.value - 1) return@PinCodeKeyboard
                    else inputValue.value += digit
                },
                actionClearOnClick = {
                    var temp = inputValue.value
                    if (temp.isNotEmpty()) {
                        temp = temp.substring(0, temp.length - 1)
                        inputValue.value = temp
                    }
                },
                actionCheckOnClick = {
                    if (isFilled&&!bool) {
                        bool=true
                        inputValue2.value = inputValue.value
                        inputValue.value = ""
                    }
                    if (isFilled && bool) {
                        event(
                            PinCodeContract.Event.Checked(
                                inputValue.value
                            )
                        )
                    }

                },
                isCheckButtonAvailable = true,
                isFilled = isFilled
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@[Preview Composable]
fun PinCodePreview() {
    MobileBankingComposeTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            PinCodeContent(
                state = mutableStateOf(PinCodeContract.State()),
                event = {})
        }
    }
}