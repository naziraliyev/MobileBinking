package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.privacyScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.AppCheckBoxApelsin
import uz.gita.mobilebankingcompose.utils.button.AppButtonApelsin

@Parcelize
class PrivacyScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("PrivacyScreen") {

    @Composable
    override fun Content() {
        PrivacyScreenContent()
    }
}

@Composable
fun PrivacyScreenContent() {
    Surface(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(24.dp)) {
        var isCheck by remember {mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.data_agreement),
                style = MaterialTheme.typography.h3.copy(fontSize = 18.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f)) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(36.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.White, Color.Transparent)
                                )
                            )
                    )

                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Text(
                            text = stringResource(id = R.string.text_policy_details),
                            modifier = Modifier.padding(top = 8.dp)
                        )

                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(18.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.White, Color.Transparent)
                            )
                        )
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 26.dp, start = 16.dp)
                            .clickable(onClick = { isCheck == !isCheck }),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppCheckBoxApelsin(
                            text = R.string.privacy_agreement,
                            onChecked = isCheck,
                            onCheckedChange = { isCheck = !isCheck },
                            modifier = Modifier.padding()
                        )

                    }
                    AppButtonApelsin(
                        text = stringResource(id = R.string.buttonText),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        onClick = { },
                        enabled = isCheck
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun PrivacyScreenContentPreview() {
    MobileBankingComposeTheme {
        PrivacyScreenContent()
    }
}