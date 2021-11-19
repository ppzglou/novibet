package gr.sppzglou.novibet.ui

import android.net.ConnectivityManager
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.novibet.base.BaseViewModel
import gr.sppzglou.novibet.di.connectivity.ConnectivityLiveData
import gr.sppzglou.novibet.framework.Repository
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
@Inject constructor(
    connectivityLiveData: ConnectivityLiveData,
    connectivityManager: ConnectivityManager,
    private val repo: Repository
) : BaseViewModel(connectivityLiveData, connectivityManager) {


}
