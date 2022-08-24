package uz.gita.mobilebankingcompose.ui.auth.pincode

import uz.gita.mobilebankingcompose.R

class PinCodeContract {
    data class State(
        val isChecked: Boolean = false,
        val textSkip: Int = R.string.text_skip
    )

    sealed class Event {
        data class Checked(val code: String) : Event()
        object Skip : Event()
        object Filled : Event()
        object UnFilled : Event()
    }
}