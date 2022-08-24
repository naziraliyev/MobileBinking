package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode

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
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.pincode.repeat.RepeatPinCodeContract
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.CirclePinCode
import uz.gita.mobilebankingcompose.utils.PinCodeKeyboardApelsin

@Parcelize
class FirstPinCodeScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("PinCodeScreenApelsin") {

    @Composable
    override fun Content() {
        val viewModel:FirstPinCodeViewModel = viewModel<FirstPinCodeViewModelImpl>()
        FirstPinCodeScreenContent(viewModel.state.collectAsState(),viewModel::onEventPin)
    }
}

@Composable
fun FirstPinCodeScreenContent(state:State< FirstPinCodeContract.State>,eventPin:( FirstPinCodeContract.EventPin)->Unit) {
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
                            onClick = { eventPin(FirstPinCodeContract.EventPin.OnBackPressed) }
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
                    text = stringResource(R.string.create_pin),
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
                numberOnClick = {

                },
                actionClearOnClick = {

                },
                actionTextOnclick = { },
            )

        }
    }

}



@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun FirstPinCodeScreenContentPreview() {
    MobileBankingComposeTheme {
        FirstPinCodeScreenContent(state = mutableStateOf(FirstPinCodeContract.State()),{})
    }
}