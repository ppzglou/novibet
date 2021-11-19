package gr.sppzglou.novibet.ui

import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.novibet.base.BaseViewModel
import gr.sppzglou.novibet.di.connectivity.ConnectivityLiveData
import gr.sppzglou.novibet.framework.Repository
import gr.sppzglou.novibet.utils.BEARER
import gr.sppzglou.single.utils.SingleLiveEvent
import gr.sppzglou.single.utils.set
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
@Inject constructor(
    connectivityLiveData: ConnectivityLiveData,
    connectivityManager: ConnectivityManager,
    private val repo: Repository,
    private val sharedPref: SharedPreferences,
) : BaseViewModel(connectivityLiveData, connectivityManager) {

    private val _successToken = SingleLiveEvent<Boolean>()
    val successToken: LiveData<Boolean> = _successToken

    fun clearToken() {
        sharedPref[BEARER] = null
    }

    fun token() {
        launch {
            repo.token()
            _successToken.value = true
        }
    }
}
