package gr.sppzglou.novibet.ui

import dagger.hilt.android.AndroidEntryPoint
import gr.sppzglou.novibet.base.BaseActivity
import gr.sppzglou.novibet.databinding.ActivityDashboardBinding


@AndroidEntryPoint
class DashboardActivity :
    BaseActivity<ActivityDashboardBinding, DashboardViewModel>(DashboardViewModel::class.java) {

    override fun getViewBinding(): ActivityDashboardBinding {
        return ActivityDashboardBinding.inflate(layoutInflater)
    }

    override fun setupObservers() {
        with(viewModel) {
            successToken.observe(this@DashboardActivity) {

            }
        }
    }

    override fun setupViews() {
        with(binding) {
            viewModel.token()
        }
    }


    override fun setupListeners() {}

    override fun onStop() {
        viewModel.clearToken()
        super.onStop()
    }
}