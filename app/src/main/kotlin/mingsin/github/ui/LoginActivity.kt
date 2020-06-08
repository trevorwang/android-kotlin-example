package mingsin.github.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.android.support.DaggerAppCompatActivity
import mingsin.github.R
import mingsin.github.databinding.ActivityLoginBinding
import mingsin.github.di.ViewModelFactory
import mingsin.github.viewmodel.LoginViewModel
import javax.inject.Inject


/**
 * Created by trevorwang on 31/12/2017.
 */
class LoginActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    val model by viewModels<LoginViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.viewModel = model
        binding.lifecycleOwner = this
        binding.btnSave.setOnClickListener {
            model.saveData {
                finish()
            }
        }
    }

}