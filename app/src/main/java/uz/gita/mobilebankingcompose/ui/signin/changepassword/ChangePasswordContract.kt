package uz.gita.mobilebankingcompose.ui.signin.changepassword

class ChangePasswordContract {

    data class State(
        val isEquals:Boolean = false
    )

    sealed class Event(){
        data class ChangePassword(val password:String):Event()
    }
}