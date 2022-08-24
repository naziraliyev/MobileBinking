package uz.gita.mobilebankingcompose.ui.auth.verify

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
import uz.gita.mobilebankingcompose.domain.verificationUsecase.VerificationUseCase
import uz.gita.mobilebankingcompose.utils.toPhoneNumberUI
import javax.inject.Inject

@HiltViewModel
class VerifyScreenViewModelImpl @Inject constructor(

    private val useCase: VerificationUseCase,
    private val direction: VerifyScreenDirection
) :
    ViewModel(), VerifyScreenViewModel {
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
                if (timer == 0L) reduce { it.copy(timerStatus = false) }
            }
        }
    }

    private fun stopTimer() {
        timer = 0
    }

    override fun initData(data: SignUpData) {
        this.data = data
        viewModelScope.launch { reduce { it.copy(phoneNumber = toPhoneNumberUI(data.phoneNumber)) } }
    }

    override fun onEvent(event: VerifyContract.Event) {
        when (event) {
            is VerifyContract.Event.OnBackPressed -> {
                job?.cancel()
                direction.back()
            }

            is VerifyContract.Event.Retry -> {
                viewModelScope.launch { reduce { it.copy(timerStatus = true) } }
                initTimer()
            }
            is VerifyContract.Event.ActionKeyboard -> {
                if (_state.value.errorStatus) reduce { it.copy(errorStatus = false) }
            }
            is VerifyContract.Event.OnRegister -> {
                stopTimer()
                reduce {
                    it.copy(
                        isKeyboardEnabled = false,
                        acceptButtonStatus = false,
                        progressStatus = true
                    )
                }
                job = useCase.signUpVerify(data = SignUpVerifyData(code = event.code))
                    .onEach { resultData ->
                        resultData.onSuccess {
                            direction.navigateToPinCode()
                            setDefaultState()
                        }
                        resultData.onText {
                            reduce {
                                it.copy(
                                    message = message,
                                    errorStatus = true,
                                    timerStatus = false
                                )
                            }
                        }
                        resultData.onResource {
                            reduce {
                                it.copy(
                                    resourceId = resourceId,
                                    errorStatus = true,
                                    timerStatus = false
                                )
                            }
                        }
                    }.launchIn(viewModelScope)

            }
            is VerifyContract.Event.Filled -> reduce { it.copy(acceptButtonStatus = true) }

            is VerifyContract.Event.UnFilled -> reduce { it.copy(acceptButtonStatus = false) }
        }
    }


    private fun setDefaultState() {
        _state.value = VerifyContract.State()
    }

    private fun reduce(content: (old: VerifyContract.State) -> VerifyContract.State) {
        val old = _state.value
        val newState = content(old)
        _state.value = newState
    }
}