package uz.gita.mobilebankingcompose.ui.onboarding

import androidx.compose.ui.graphics.Color
import uz.gita.mobilebankingcompose.R


val pages = listOf(
    Page(
        id = 1,
        content = PageContent(R.drawable.img_1, "Choose your interests"),
        color = Color(0xFF103E85)
    ),
    Page(
        id = 2,
        content = PageContent(R.drawable.img_2, "Control your money"),
        color = Color(0xFFD45D9E)
    ),
    Page(
        id = 3,
        content = PageContent(R.drawable.img_3, "Exchange your Money on Time"),
        color = Color(0xFFFFFFFF)
    ),
    Page(
        id = 4,
        content = PageContent(R.drawable.img_4, "Easy transfer With online "),
        color = Color(0xFF52C6DF)
    ),
)

data class Page(
    val id: Int,
    val content: PageContent,
    val color: Color
)

data class PageContent(
    val imageId: Int,
    val text: String
)