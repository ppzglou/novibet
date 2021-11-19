package gr.sppzglou.novibet.ui

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.novibet.base.BaseViewModel
import gr.sppzglou.novibet.di.connectivity.ConnectivityLiveData
import gr.sppzglou.single.utils.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    connectivityLiveData: ConnectivityLiveData,
    connectivityManager: ConnectivityManager,
) : BaseViewModel(connectivityLiveData, connectivityManager) {

    val navigateChoiceV = SingleLiveEvent<Int>()
    val navigateChoice: LiveData<Int> = navigateChoiceV

    @SuppressLint("StaticFieldLeak")
    lateinit var pager: ViewPager2

}
