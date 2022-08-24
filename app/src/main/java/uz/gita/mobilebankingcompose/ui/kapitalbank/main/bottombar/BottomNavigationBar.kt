package uz.gita.mobilebankingcompose.ui.kapitalbank.main.bottombar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import uz.gita.mobilebankingcompose.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = colorResource(id =R.color.background_A)) {
        val backStackEntity by navController.currentBackStackEntryAsState()
        val currentState = backStackEntity?.destination?.route

        NavData.BarItems.forEach { items ->
            BottomNavigationItem(
                unselectedContentColor = colorResource(id = R.color.brentColor),
                selectedContentColor = colorResource(id = R.color.text_grey),
                selected = currentState == items.route,
                onClick = {
                    navController.navigate(items.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = items.icon, contentDescription = items.title)
                },
                label = { Text(text = items.title) }
            )
        }
    }
}