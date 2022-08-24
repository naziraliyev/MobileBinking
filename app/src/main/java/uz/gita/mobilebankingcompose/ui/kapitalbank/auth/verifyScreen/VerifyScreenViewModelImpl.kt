package uz.gita.mobilebankingcompose.ui.kapitalbank.auth.verifyScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.mobilebankingcompose.data.model.auth.SignUpData
import uz.gita.mobilebankingcompose.data.model.auth.SignUpVerifyData
import uz.gita.mobilebankingcompose.data.model.onResource
import uz.gita.mobilebankingcompose.data.model.onSuccess
import uz.gita.mobilebankingcompose.data.model.onText
import uz.gita.mobilebankingcompose.domain.kapitalbank.authUsecases.VerifyUseCaseApelsin
import uz.gita.mobilebankingcompose.domain.verificationUsecase.VerificationUseCase
import uz.gita.mobilebankingcompose.utils.toPhoneNumberUI
import javax.inject.Inject

@HiltViewModel
class VerifyScreenViewModelImpl @Inject constructor(
    private val verifyDirection: VerifyDirection,
    private val verificationUseCase: VerifyUseCaseApelsin
) : ViewModel(), VerifyViewModel {

    private val _state = MutableStateFlow(VerifyContract.State())
    override val state = _state.asStateFlow()

    private var timer = _state.value.timer
    private var job: Job? = null
    private var data: SignUpData? = null

    init {
        initTimer()
    }

    private fun initTimer() {
        if (timer == 0L) timer = 60000L
        viewModelScope.launch {
            while (timer != 0L) {
                delay(1000)
                reduce { it.copy(timer = timer) }
                timer -= 1000L
                if (timer == 0L) reduce { it.copy(timeStatus = true) }
            }
        }
    }

    override fun onEvent(event: VerifyContract.Event) {
        when (event) {
            is VerifyContract.Event.OnBackPressed -> {
                job?.cancel()
                verifyDirection.back()
            }
            is VerifyContract.Event.Retry -> {
                viewModelScope.launch { reduce { it.copy(timeStatus = true) } }
                initTimer()
            }
            is VerifyContract.Event.ActionKeyBoard -> {
                if (_state.value.errorStatus) reduce { it.copy(errorStatus = false) }
            }
            is VerifyContract.Event.Verified -> {
                stopTimer()
                reduce {
                    it.copy(
                        isKeyboardEnable = false,
                        progressStatus = true,
                        buttonStatus = false
                    )
                }
                job = verificationUseCase.signUpVerify(data = SignUpVerifyData(code = event.code))
                    .onEach { resultData ->
                        resultData.onSuccess {
                            verifyDirection.navigateNextScreen()
                            setDefaultState()
                        }
                        resultData.onText {
                            reduce {
                                it.copy(
                                    message = message,
                                    errorStatus = true,
                                    timeStatus = false
                                )
                            }

                        }
                        resultData.onResource {
                            reduce { it.copy(
                                resourceId = resourceId,
                                errorStatus = true,
                                timeStatus = false
                            ) }
                        }

                    }.launchIn(viewModelScope)

            }
            is VerifyContract.Event.FilledApelsin->{reduce { it.copy(buttonStatus = true) }}
            is VerifyContract.Event.UnFilled->{reduce { it.copy(buttonStatus = false) }}
        }

    }

    private fun setDefaultState() {
        _state.value = VerifyContract.State()
    }

    private fun stopTimer() {
        timer = 0L
    }

    override fun initData(data: SignUpData) {
        this.data = data
        viewModelScope.launch { reduce { it.copy(phoneNumber = toPhoneNumberUI(data.phoneNumber)) } }
    }

    fun reduce(content: (old: VerifyContract.State) -> VerifyContract.State) {
        val oldData = _state.value
        val newData = content(oldData)
        _state.value = newData
    }
}