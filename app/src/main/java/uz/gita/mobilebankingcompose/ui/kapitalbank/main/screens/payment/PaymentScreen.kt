package uz.gita.mobilebankingcompose.ui.kapitalbank.main.screens.payment

import androidx.compose.runtime.Composable
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize

@Parcelize
class PaymentScreen (override val screenKey: String = uniqueScreenKey):ComposeScreen("PaymentScreen"){

    @Composable
    override fun Content() {

    }
}

@Composable
fun PaymentScreenContent(){

}