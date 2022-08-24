package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.laguage

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import javax.inject.Inject

@Parcelize
class LanguageScreenApelsin (override val screenKey: String = uniqueScreenKey):ComposeScreen("LanguageScreenApelsin") {

    @Composable
    override fun Content() {
        LanguageScreenApelsinContent()
    }
}

@Composable
fun LanguageScreenApelsinContent(){

}

@Preview
@Composable
fun LanguageScreenApelsinContentPreview(){
    MobileBankingComposeTheme {
        LanguageScreenApelsinContent()
    }
}