package com.example.hydro.scenes.main
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {

    private val _event: MutableLiveData<MainEvent> = MutableLiveData<MainEvent>()
    val event: LiveData<MainEvent> = _event

    private val _progress = MutableStateFlow(0.0f)
    val progress = _progress

    val uiState = MainUIState()

    fun handleWaterValue(liters: Float) {
        _progress.value += liters
    }

    sealed interface MainEvent {
        class HandleError(val errorMessage: String) : MainEvent
    }

    class MainUIState(
        private val _enableLoadIndicator: MutableLiveData<Boolean> = MutableLiveData(),
        private val _closeLoadDialog: MutableLiveData<Boolean> = MutableLiveData(),
        private val _waterValue: MutableLiveData<Float> = MutableLiveData(),

        ) {
        val enableLoadIndicator: LiveData<Boolean> = _enableLoadIndicator
        val closeLoadDialog: LiveData<Boolean> = _closeLoadDialog
        val waterValue: LiveData<Float> = _waterValue

        fun showLoadIndicator(enable: Boolean) = _enableLoadIndicator.run { postValue(enable) }
        fun closeLoadDialog(enable: Boolean) = _closeLoadDialog.run { postValue(enable) }
        fun incrementWaterValue(value: Float) = _waterValue.run { postValue(value) }
    }

}