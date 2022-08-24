package uz.gita.mobilebankingcompose.ui.onboarding.onboard

import androidx.compose.ui.graphics.Color
import uz.gita.mobilebankingcompose.R

data class SampleOnBoard(
    val image: Int,
    val title: String,
    val desc: String
)

val onBoardItem = listOf(
    SampleOnBoard(
        R.drawable.img_1,
        "Make it Easy One",
        "Lorem Ipsum is simply dummy text of the printing and typesetting Industry."
    ),
    SampleOnBoard(
        R.drawable.img_2,
        "Make it Easy Two",
        "Lorem Ipsum is simply dummy text of the printing and typesetting Industry."
    ),
    SampleOnBoard(
        R.drawable.img_3,
        "Make it Easy Three",
        "Lorem Ipsum is simply dummy text of the printing and typesetting Industry."
    )
)