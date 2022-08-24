package uz.gita.mobilebankingcompose.ui.auth.language

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.data.model.LanguageModel
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

@Parcelize
class LanguageScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("LanguageScreen") {
    @Composable
    override fun Content() {
        val viewModel: LanguageScreenViewModel = viewModel<LanguageScreenViewModelImpl>()

        LanguageScreenContent(viewModel.uiState.collectAsState(), viewModel::eventDispatcher)
    }
}

@Composable
fun LanguageScreenContent(
    state: State<LanguageContract.State>,
    event: (event: LanguageContract.Event) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Text(
                modifier = Modifier
                    .padding(top = 120.dp)
                    .fillMaxWidth(),
                color = Color.Black,
                text = "Mobile Banking",
                fontSize = 32.sp, textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(60.dp)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentHeight()
                    .padding(4.dp)
                    .fillMaxWidth(0.9f)
                    .clickable(enabled = true, null, null, onClick = {
                        event.invoke(
                            LanguageContract.Event.AcceptedLanguage(
                                language = state.value.language[0]
                            )
                        )
                    })
                    .padding(0.dp, 20.dp),
                color = Color.Black,
                text = stringResource(id = R.string.russian),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Divider(
                color = colorResource(id = R.color.dividerLine),
                thickness = 1.dp,
                modifier = Modifier.padding(end = 25.dp, start = 25.dp)
            )


            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(4.dp)
                    .fillMaxWidth(0.9f)
                    .clickable(enabled = true, null, null, onClick = {
                        event.invoke(
                            LanguageContract.Event.AcceptedLanguage(
                                language = state.value.language.get(
                                    1
                                )
                            )
                        )
                    })
                    .padding(0.dp, 20.dp),
                color = Color.Black,
                text = stringResource(id = R.string.uzbek),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )

            Divider(
                color = colorResource(id = R.color.dividerLine),
                thickness = 1.dp,
                modifier = Modifier.padding(end = 25.dp, start = 25.dp)
            )

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(4.dp)
                    .fillMaxWidth(0.9f)
                    .clickable(enabled = true, null, null, onClick = {
                        event.invoke(
                            LanguageContract.Event.AcceptedLanguage(
                                language = state.value.language.get(
                                    2
                                )
                            )
                        )
                    })
                    .padding(0.dp, 20.dp),
                color = Color.Black,
                text = stringResource(id = R.string.english),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun LanguageScreenPreview() {
    MobileBankingComposeTheme {
        LanguageScreenContent(
            state = mutableStateOf(
                    LanguageContract.State(
                        currentLanguage = "",
                        language = listOf(
                            LanguageModel.RU.value,
                            LanguageModel.UZ.value,
                            LanguageModel.EN.value
                        ),
                    )
            )

        ) {}
    }
}