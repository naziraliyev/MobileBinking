package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.splashscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

@Parcelize
class SplashScreenApelsin(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("SplashScreen") {

    @Composable
    override fun Content() {
        val viewModel:SplashViewModel = viewModel<SplashViewModelImpl>()
        SplashScreenContent()
    }
}

@Composable
fun SplashScreenContent() {
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Kapital bank", fontSize = 24.sp)
        }

    }
}
@Preview
@Composable
fun SplashScreenContentPreview() {
    MobileBankingComposeTheme {
        SplashScreenContent()
    }
}