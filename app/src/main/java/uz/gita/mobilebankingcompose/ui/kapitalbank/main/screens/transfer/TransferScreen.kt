@file:OptIn(ExperimentalMaterialApi::class)

package uz.gita.mobilebankingcompose.ui.kapitalbank.main.screens.transfer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.Parcelize
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

@Parcelize
class TransferScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("TransferScreen") {

    @Composable
    override fun Content() {

    }
}

@Composable
fun TransferScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        val list = listOf(
            TransferData(
                title = stringResource(R.string.transfer_card),
                icon = R.drawable.ic_back_ios,
                icon2 = R.drawable.ic_baseline_arrow_forward_ios_24
            ),
            TransferData(
                title = stringResource(R.string.transfer_wallet),
                icon = R.drawable.ic_back_ios,
                icon2 = R.drawable.ic_baseline_arrow_forward_ios_24
            ),
            TransferData(
                title = stringResource(R.string.transfer_account),
                icon = R.drawable.ic_back_ios,
                icon2 = R.drawable.ic_baseline_arrow_forward_ios_24
            ),
            TransferData(
                title = stringResource(R.string.transfer_requisites),
                icon = R.drawable.ic_back_ios,
                icon2 = R.drawable.ic_baseline_arrow_forward_ios_24
            ),
            TransferData(
                title = stringResource(R.string.transfer_fundraising),
                icon = R.drawable.ic_back_ios,
                icon2 = R.drawable.ic_baseline_arrow_forward_ios_24
            ),
            TransferData(
                title = stringResource(R.string.transfer_rocket),
                icon = R.drawable.ic_back_ios,
                icon2 = R.drawable.ic_baseline_arrow_forward_ios_24
            )
        )

        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.transfer),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                backgroundColor = MaterialTheme.colors.surface
            )
        }) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(list) { item ->
                    TransferItems(title = item.title, icon = item.icon, icon2 = item.icon2, onClickItem = {})
                }
            }
        }

    }


}

@Composable
fun TransferItems(title: String, icon: Int, icon2: Int,onClickItem :()->Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            elevation = 5.dp,
            backgroundColor = colorResource(id = R.color.background_A),
            onClick = {onClickItem}
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.brentColor))
                )
                Surface(modifier = Modifier.padding(16.dp)) {}
                Text(text = title, color = colorResource(id = R.color.text_black))
                Surface(modifier = Modifier.weight(1f)) { }
                Image(
                    painter = painterResource(id = icon2),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.brentColor))
                )
            }
        }
    }
}

data class TransferData(
    val icon: Int,
    val title: String,
    val icon2: Int
)

@Preview
@Composable
fun TransferScreenPreview() {
    MobileBankingComposeTheme {
        TransferScreenContent()
    }
}