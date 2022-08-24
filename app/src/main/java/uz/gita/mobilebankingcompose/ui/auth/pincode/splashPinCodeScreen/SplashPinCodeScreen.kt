package uz.gita.mobilebankingcompose.ui.auth.pincode.splashPinCodeScreen

import android.Manifest
import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.terrakok.modo.android.compose.ComposeScreen
import com.github.terrakok.modo.android.compose.uniqueScreenKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import uz.gita.mobilebankingcompose.R
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme
import uz.gita.mobilebankingcompose.utils.PinCode
import uz.gita.mobilebankingcompose.utils.PinCodeKeyboard

@Parcelize
class SplashPinCodeScreen(override val screenKey: String = uniqueScreenKey) :
    ComposeScreen("splashPinCodeScreen") {


    @IgnoredOnParcel
    private var cancellationSignal: CancellationSignal? = null

    @Composable
    override fun Content() {
        val viewModel: SplashPinCodeViewModel = viewModel<SplashPinCodeViewModelImpl>()

        val context = LocalContext.current
        viewModel.sideEffect { sideEffect ->
            when (sideEffect) {
                SplashPinCodeContract.SideEffect.FINGER_PRINT -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        launchBiometric(context)
                    }
                }
            }
        }
        SplashPinCodeScreenContent(viewModel.state.collectAsState(), viewModel::onEvent)
    }

    @IgnoredOnParcel
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback =
        @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                Timber.d("authenticationCallback: onAuthenticationSucceeded")

            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                Timber.d("authenticationCallback: onAuthenticationError")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Timber.d("authenticationCallback: onAuthenticationFailed")
            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
                Timber.d("authenticationCallback: onAuthenticationHelp")
            }
        }

    private fun checkBiometricSupport(context: Context): Boolean {
        val keyGuardManager = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyGuardManager.isDeviceSecure) {
            return true
        }
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.USE_BIOMETRIC
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }

        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun launchBiometric(context: Context) {
        if (checkBiometricSupport(context)) {
            val biometricPrompt = BiometricPrompt.Builder(context)
                .apply {
                    setTitle(context.getString(R.string.prompt_info_title))
                    setSubtitle(context.getString(R.string.prompt_info_subtitle))
                    setDescription(context.getString(R.string.prompt_info_description))
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        setConfirmationRequired(true)
                    }
                    setNegativeButton(
                        context.getString(R.string.prompt_info_use_app_password),
                        context.mainExecutor
                    ) { _, _ ->

                    }
                }.build()

            biometricPrompt.authenticate(
                getCancellationSignal(),
                context.mainExecutor,
                authenticationCallback
            )
        }
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            Timber.tag("TTT").d("getCancellationSignal")
        }

        return cancellationSignal as CancellationSignal
    }

}

@Composable
fun SplashPinCodeScreenContent(
    state: State<SplashPinCodeContract.State>,
    event: (SplashPinCodeContract.Event) -> Unit
) {


    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout {
            val (title, pinCodeRow, customKeyboard) = createRefs()
            val topGuideLineText = createGuidelineFromTop(0.146875f)
            val topGuideLinePinCode = createGuidelineFromTop(0.23125f)
            val topGuideLineKeyboard = createGuidelineFromTop(0.3375f)
            val inputValue = remember { mutableStateOf("") }
            val maxLength = remember { mutableStateOf(4) }

            if (state.value.isError) inputValue.value = ""

            Text(
                text = stringResource(id = state.value.textPinCode),
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topGuideLineText)
                },
                style = MaterialTheme.typography.body2.copy(
                    lineHeight = 22.sp
                )
            )

            PinCode(
                modifier = Modifier
                    .width(96.dp)
                    .constrainAs(pinCodeRow) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topGuideLinePinCode)
                    },
                currentLength = inputValue.value.length,
                maxLength = 4
            )

            PinCodeKeyboard(
                modifier = Modifier.constrainAs(customKeyboard) {
                    linkTo(start = parent.start, end = parent.end)
                    linkTo(top = topGuideLineKeyboard, bottom = parent.bottom)
                },
                numberOnClick = { digit ->
                    if (inputValue.value.length < maxLength.value - 1) inputValue.value += digit
                    else {
                        inputValue.value += digit
                        event(SplashPinCodeContract.Event.EnterCode(inputValue.value))
                    }
                },
                actionClearOnClick = {
                    var temp = inputValue.value
                    if (temp.isNotEmpty()) {
                        temp = temp.substring(0, temp.length - 1)
                        inputValue.value = temp
                    }
                },
                actionFingerPrintOnClick = {
                    event.invoke(SplashPinCodeContract.Event.FingerPrint)
                },
                isFingerPrintAvailable = state.value.isFingerPrint
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun SplashPinCodeScreenContentPreview() {
    MobileBankingComposeTheme {
        SplashPinCodeScreenContent(state = mutableStateOf(SplashPinCodeContract.State()),
            event = {})
    }
}