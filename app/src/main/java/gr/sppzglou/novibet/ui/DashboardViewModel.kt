package gr.sppzglou.novibet.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.novibet.base.BaseViewModel
import gr.sppzglou.single.utils.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
) : BaseViewModel() {

    val navigateChoiceV = SingleLiveEvent<Int>()
    val navigateChoice: LiveData<Int> = navigateChoiceV

    @SuppressLint("StaticFieldLeak")
    lateinit var pager: ViewPager2

}
