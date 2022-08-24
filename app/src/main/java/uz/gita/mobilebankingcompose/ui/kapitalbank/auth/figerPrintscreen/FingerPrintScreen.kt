package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.figerPrintscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

@Parcelize
class FingerPrintScreen(override val screenKey: String = uniqueScreenKey):ComposeScreen("FingerPrintScreen") {

    @Composable
    override fun Content() {
        FingerPrintScreenContent()
    }
}

@Composable
fun FingerPrintScreenContent(){

}
@Preview
@Composable
fun FingerPrintScreenContentPreview(){
    MobileBankingComposeTheme {
        FingerPrintScreenContent()
    }
}