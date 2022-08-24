package uz.gita.mobilebankingcompose.ui.kapitalbank.main.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.ui.kapitalbank.main.bottombar.BottomNavigationBar
import uz.gita.mobilebankingcompose.ui.kapitalbank.main.bottombar.NavigationRoutes
import uz.gita.mobilebankingcompose.ui.kapitalbank.main.screens.chat.ChatScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.main.screens.homeScreen.HomeScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.main.screens.menu.MenuScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.main.screens.payment.PaymentScreen
import uz.gita.mobilebankingcompose.ui.kapitalbank.main.screens.transfer.TransferScreen
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

@Parcelize
class MainScreenApelsin(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("MainScreen") {
    @Composable
    override fun Content() {
        MainScreenContent()
    }
}

@Composable
fun MainScreenContent() {
    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {

        val navController = rememberNavController()

        Scaffold(
            content = { NavigationHost(navController = navController) },
            bottomBar = { BottomNavigationBar(navController = navController)}
        )
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoutes.Home.routes) {
        composable(route = NavigationRoutes.Home.routes) {
            HomeScreen()
        }

        composable(route = NavigationRoutes.Transfer.routes) {
            TransferScreen()
        }

        composable(route = NavigationRoutes.Payment.routes) {
            PaymentScreen()
        }

        composable(route = NavigationRoutes.Chat.routes) {
            ChatScreen()
        }

        composable(route = NavigationRoutes.Menu.routes) {
            MenuScreen()
        }

    }
}

@Preview
@Composable
fun MainScreenContentPreview() {
    MobileBankingComposeTheme {
        MainScreenContent()
    }
}