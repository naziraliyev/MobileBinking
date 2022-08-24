package uz.gita.mobilebankingcompose.ui.auth.privacy

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebanking.utils.customui.AppIcon
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.AppCheckBox
import uz.gita.mobilebankingcompose.utils.button.AppButton

@Parcelize
class PrivacyPolicyScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("PrivacyPolicy") {

    @Composable
    override fun Content() {
        val viewModel: PrivacyViewModel = viewModel<PrivacyViewModelImpl>()
        PrivacyPolicyScreenContent(viewModel.state.collectAsState(), viewModel::onEvent)
    }
}

@Composable
fun PrivacyPolicyScreenContent(
    state: State<PrivacyContract.State>,
    event: (PrivacyContract.Event) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(36.dp)
                .background(brush = Brush.verticalGradient(
                    colors = listOf(Color.White,Color.Transparent)
                )))
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)) {

                Text(
                    text = stringResource(id = R.string.text_policy_title),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )

                AppIcon(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 48.dp),
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.text_policy_subtitle),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.text_policy_details),
                    style = MaterialTheme.typography.subtitle2,
                )
                val isCheck =state.value.buttonAcceptStatus
                Row(
                    modifier = Modifier.padding(top = 16.dp, bottom = 26.dp, start = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {


                    AppCheckBox(
                        onChecked = isCheck,
                        onCheckedChange = { event(PrivacyContract.Event.CHECK) },
                        modifier = Modifier.padding()
                    )


                }
                AppButton(
                    onClick = { event(PrivacyContract.Event.ACCEPT) },
                    modifier = Modifier
                        .padding( start = 16.dp, end = 16.dp, bottom = 32.dp),
                    enabled = isCheck,
                    text = stringResource(id = R.string.text_accept),
                )

            }

        }
    }

}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun PrivacyPolicyScreenContentPreview() {
    MobileBankingComposeTheme {
        PrivacyPolicyScreenContent(state = mutableStateOf(PrivacyContract.State()), {})
    }
}