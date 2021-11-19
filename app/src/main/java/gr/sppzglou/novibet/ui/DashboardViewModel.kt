package gr.sppzglou.novibet.ui

import android.content.SharedPreferences
import android.net.ConnectivityManager
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.novibet.base.BaseViewModel
import gr.sppzglou.novibet.di.connectivity.ConnectivityLiveData
import gr.sppzglou.novibet.framework.Repository
import gr.sppzglou.novibet.utils.BEARER
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

    fun clearToken() {
        sharedPref[BEARER] = null
    }
}
