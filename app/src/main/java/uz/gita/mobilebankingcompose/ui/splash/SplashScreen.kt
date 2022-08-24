package uz.gita.mobilebankingcompose.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

@Parcelize
class SplashScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("SplashScreen") {

    @Composable
    override fun Content() {
        val viewModel: SplashScreenViewModel = viewModel<SplashScreenViewModelImpl>()
        SplashScreenContent()
    }
}

@Composable
fun SplashScreenContent() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_splash)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.ic_group), contentDescription = "")
            Image(painter = painterResource(id = R.drawable.ic_gita_bank), contentDescription = "")
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