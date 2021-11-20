package gr.sppzglou.novibet.base

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gr.sppzglou.novibet.di.connectivity.ConnectivityLiveData
import gr.sppzglou.novibet.di.connectivity.ConnectivityStatus
import gr.sppzglou.novibet.di.connectivity.isNetworkConnected
import gr.sppzglou.single.utils.Event
import gr.sppzglou.single.utils.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    val connectivityLiveData: ConnectivityLiveData,
    private val connectivityManager: ConnectivityManager
) : ViewModel() {

    private val _connectivityUI = MutableLiveData<Event<ConnectivityStatus>>()
    val connectivityUI: LiveData<Event<ConnectivityStatus>> = _connectivityUI

    val _loadError = MutableLiveData<Boolean>()
    val loadError: LiveData<Boolean> = _loadError

    val error = SingleLiveEvent<Exception>()

    fun launch(delayTime: Int = 0, function: suspend () -> Unit) {
        viewModelScope.launch {
            delay(delayTime.toLong())
            try {
                function.invoke()
            } catch (e: Exception) {
                error.value = e
            }
        }
    }

    fun checkConnectivity() {
        _connectivityUI.value = when {
            connectivityManager.isNetworkConnected -> Event(ConnectivityStatus.Connected)
            else -> Event(ConnectivityStatus.Disconnected)
        }
    }
}