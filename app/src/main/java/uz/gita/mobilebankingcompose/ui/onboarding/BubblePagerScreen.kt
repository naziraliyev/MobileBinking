@file:OptIn(ExperimentalPagerApi::class)

package uz.gita.mobilebankingcompose.ui.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import timber.log.Timber
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

@OptIn(ExperimentalPagerApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MobileBankingComposeTheme {
                val pagerState = rememberPagerState()

                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = pagerState.currentPage == 2
                )
                Surface(modifier = Modifier.fillMaxSize()) {
                    BubblePagerContent(pagerState = pagerState)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BubblePagerContent(pagerState: PagerState) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        BubblePager(
            pagerState = pagerState,
            pageCount = pages.size,
            modifier = Modifier.fillMaxSize(),
            bubbleColors = pages.map { it.color }
        ) { page ->
            Timber.d("page $page")
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(120.dp))
                Image(
                    modifier = Modifier.size(250.dp),
                    painter = painterResource(id = pages[page].content.imageId),
                    contentDescription = "Image"
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = pages[page].content.text,
                    style = MaterialTheme.typography.h3,
                    color = if (page == 2) Color.Black else Color.White,
                    textAlign = TextAlign.Center,
                    softWrap = true,
                    modifier = Modifier.padding(horizontal = 40.dp)
                )
            }
        }
        PagerTopAppBar(
            page = pagerState.currentPage,
            modifier = Modifier
                .wrapContentSize()
                .statusBarsPadding()
        )
    }
}

@Composable
fun PagerTopAppBar(page: Int, modifier: Modifier = Modifier) {
    TopAppBar(
        title = { },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Left Icon",
                )
            }
        },

        contentColor = if (page == 2) Color.Black else Color.White,
        modifier = modifier
    )
}

@Composable
@Preview
fun PreviewCOntent(){
    MobileBankingComposeTheme {
        val pagerState = rememberPagerState()

        val systemUiController = rememberSystemUiController()
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = pagerState.currentPage == 2
        )
        BubblePagerContent(pagerState)
    }
}
