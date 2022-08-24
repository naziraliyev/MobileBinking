package uz.gita.mobilebankingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.android.compose.*
import com.github.terrakok.modo.back
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebankingcompose.ui.kapitalbank.auth.splashscreen.SplashScreenApelsin
import uz.gita.mobilebankingcompose.ui.splash.SplashScreen
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var modo: Modo

    @OptIn(ExperimentalAnimationApi::class)
    private val render = ComposeRenderImpl(this) {
        ScreenTransition(
            transitionSpec = {
                if (transitionType == ScreenTransitionType.Replace) {
                    scaleIn(initialScale = 2f) + fadeIn() with fadeOut()
                } else {
                    val (initialOffset, targetOffset) = when (transitionType) {
                        ScreenTransitionType.Pop -> ({ size: Int -> -size }) to ({ size: Int -> size })
                        else -> ({ size: Int -> size }) to ({ size: Int -> -size })
                    }
                    slideInHorizontally(initialOffsetX = initialOffset) with
                            slideOutHorizontally(targetOffsetX = targetOffset)
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        modo.init(savedInstanceState) { SplashScreenApelsin()}
        setContent { MobileBankingComposeTheme { render.Content() } }
    }

    override fun onResume() {
        super.onResume()
        modo.render = render
    }

    override fun onPause() {
        super.onPause()
        modo.render = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        modo.saveState(outState)
    }

    override fun onBackPressed() {
        modo.back()
    }
}