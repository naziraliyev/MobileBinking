package uz.gita.mobilebankingcompose.ui.kapitalbank.main.bottombar

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItems(
    val title:String,
    val icon:ImageVector,
    val route:String
)

object NavData {
    val BarItems = listOf(
        NavBarItems(
            title = "Home",
            icon = Icons.Default.Home,
            route = "home"
        ),
        NavBarItems(
            title = "Transfer",
            icon = Icons.Default.Transform,
            route = "transfer"
        ),
        NavBarItems(
            title = "Payment",
            icon = Icons.Default.Payment,
            route = "payment"
        ),
        NavBarItems(
            title = "Chat",
            icon = Icons.Default.Chat,
            route = "chat"
        ),
        NavBarItems(
            title = "Menu",
            icon = Icons.Default.Menu,
            route = "menu"
        ),
    )
}