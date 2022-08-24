package uz.gita.mobilebankingcompose.ui.kapitalbank.main.bottombar

sealed class NavigationRoutes (val routes: String){
    object Home:NavigationRoutes("home")
    object Transfer:NavigationRoutes("transfer")
    object Payment:NavigationRoutes("payment")
    object Chat:NavigationRoutes("chat")
    object Menu:NavigationRoutes("menu")
}