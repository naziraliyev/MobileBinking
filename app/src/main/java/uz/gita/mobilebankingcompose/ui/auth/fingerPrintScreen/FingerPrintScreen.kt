package uz.gita.mobilebankingcompose.ui.auth.fingerPrintScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize

@Parcelize
class FingerPrintScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("FingerPrintScreen") {
    @Composable
    override fun Content() {

    }
}
@Composable
fun FingerPrintScreenContent(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "ADfasgsndmfhfgfds")
    }
}