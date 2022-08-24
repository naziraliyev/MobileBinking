package uz.gita.mobilebankingcompose.ui.kapitalbank.main.screens.homeScreen

import androidx.compose.runtime.Composable
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize

@Parcelize
class HomeScreen(override val screenKey: String= uniqueScreenKey ):ComposeScreen("HomeScreen") {

   @Composable
    override fun Content() {

    }
}

@Composable
fun HomeScreenContent(){

}
