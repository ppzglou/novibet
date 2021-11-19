package gr.sppzglou.novibet.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import gr.sppzglou.novibet.di.connectivity.ConnectivityStatus
import gr.sppzglou.novibet.ui.DashboardActivity

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(clazz: Class<VM>) :
    AppCompatActivity() {

    lateinit var binding: VB

    abstract fun getViewBinding(): VB
    abstract fun setupObservers()
    abstract fun setupViews()
    abstract fun setupListeners()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        with(viewModel) {
            connectivityLiveData.observe(this@BaseActivity) { event ->
                event.getContentIfNotHandled()?.let(this@BaseActivity::connectivityChange)
            }

            connectivityUI.observe(this@BaseActivity) { event ->
                event.getContentIfNotHandled()?.let(this@BaseActivity::connectivityChange)
            }

            error.observe(this@BaseActivity, { message ->
                //snackBar(message.getMessage(this))
            })

            load.observe(this@BaseActivity) { event ->
                event.getContentIfNotHandled()?.let {

                }
            }
        }

        setupObservers()
        viewModel.checkConnectivity()
        setupViews()
        setupListeners()
    }

    protected val viewModel: VM by lazy { ViewModelProvider(this).get(clazz) }

    private fun connectivityChange(connectivityState: ConnectivityStatus) {
        val bar = (this as DashboardActivity).binding.noInternet

        when (connectivityState) {
            ConnectivityStatus.Connected -> bar.isVisible = false
            else -> bar.isVisible = true
        }
    }


}