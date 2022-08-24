package uz.gita.mobilebankingcompose.ui.mainpages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize

@Parcelize
class MainScreen(override val screenKey: String= uniqueScreenKey):ComposeScreen("mainScreen") {

    @Composable
    override fun Content() {

    }
}

@Composable
fun MainScreenContent(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "Main screen")
    }
}