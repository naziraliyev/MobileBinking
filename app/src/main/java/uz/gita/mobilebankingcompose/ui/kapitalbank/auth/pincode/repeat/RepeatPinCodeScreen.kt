package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.CirclePinCode
import uz.gita.mobilebankingcompose.utils.PinCodeKeyboardApelsin

@Parcelize
class RepeatPinCodeScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("RepeatPinCodeScreen") {

    @Composable
    override fun Content() {
        val viewModel: RepeatPinCodeViewModel = viewModel<RepeatPinCodeViewModelImpl>()
        RepeatPinCodeScreenContent(viewModel.state.collectAsState(), viewModel::onEventPin)
    }
}

@Composable
fun RepeatPinCodeScreenContent(
    state: State<RepeatPinCodeContract.State>,
    eventPin: (RepeatPinCodeContract.EventPin) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background_A))
    ) {

        val inputValue = remember { mutableStateOf("") }
        val maxLength = remember { mutableStateOf(4) }

        val isFilled = maxLength.value == inputValue.value.length

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.background_A))
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back_ios),
                        contentDescription = "",
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                            onClick = { eventPin(RepeatPinCodeContract.EventPin.OnBackPressed) }
                        )
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = stringResource(R.string.pin),
                    style = MaterialTheme.typography.h3.copy(fontSize = 32.sp),
                    modifier = Modifier.padding(start = 24.dp)
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = stringResource(R.string.repeat_pin),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(start = 24.dp)
                )
                Spacer(modifier = Modifier.padding(64.dp))

                CirclePinCode(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 72.dp, end = 72.dp),
                    currentLength = inputValue.value.length,
                    maxLength = maxLength.value
                )
                Spacer(modifier = Modifier.padding(16.dp))
            }
            PinCodeKeyboardApelsin(
                modifier = Modifier,
                numberOnClick = { digit ->
                    if (inputValue.value.length > maxLength.value - 1) return@PinCodeKeyboardApelsin
                    else inputValue.value += digit
                    if (isFilled) {
                        eventPin(RepeatPinCodeContract.EventPin.SendCode(code = inputValue.value))
                    }
                },
                actionClearOnClick = {
                    var temp = inputValue.value
                    if (temp.isNotEmpty()) {
                        temp = temp.substring(0, temp.length - 1)
                        inputValue.value = temp
                    }
                },
                actionTextOnclick = {
                    eventPin(RepeatPinCodeContract.EventPin.OnBackPressed)
                },
            )

        }
    }

}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun RepeatPinCodeContentPreview() {
    MobileBankingComposeTheme {
        RepeatPinCodeScreenContent(state = mutableStateOf(RepeatPinCodeContract.State()), {})
    }
}