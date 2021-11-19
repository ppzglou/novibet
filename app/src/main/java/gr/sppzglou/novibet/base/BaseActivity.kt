package gr.sppzglou.novibet.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

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

        viewModel.error.observe(this, { message ->
            //snackBar(message.getMessage(this))
        })

        viewModel.load.observe(this) { event ->
            event.getContentIfNotHandled()?.let {

            }
        }

        setupObservers()
        setupViews()
        setupListeners()
    }

    protected val viewModel: VM by lazy { ViewModelProvider(this).get(clazz) }


}