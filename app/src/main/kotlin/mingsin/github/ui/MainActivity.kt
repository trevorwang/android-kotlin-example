package mingsin.github.ui


import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import mingsin.github.R
import mingsin.github.databinding.ActivityMainBinding
import mingsin.github.databinding.NavHeaderMainBinding
import mingsin.github.di.ViewModelFactory
import mingsin.github.viewmodel.DrawerViewModel
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private val fragmentList: SparseArray<BaseFragment> = SparseArray()

    @Inject
    lateinit var factory: ViewModelFactory
    val viewModel by viewModels<DrawerViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setSupportActionBar(binding.appBar.toolbar)

        drawer = binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, binding.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        val navBinding = NavHeaderMainBinding.bind(binding.navView.getHeaderView(0))
        navBinding.lifecycleOwner = this
        navBinding.viewModel = viewModel
        navBinding.ivAvatar.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
        selectDrawerMenuItem(R.id.nav_trending)
    }


    private fun switchTo(fragment: androidx.fragment.app.Fragment?) {
        if (fragment == null) {
            return
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(fragment.tag).commit()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            if (supportFragmentManager.backStackEntryCount > 1) {
                super.onBackPressed()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        selectDrawerMenuItem(item.itemId)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun selectDrawerMenuItem(itemId: Int) {
        var fragment = fragmentList.get(itemId)
        if (fragment == null) {
            when (itemId) {
                R.id.nav_dashboard -> {
                    fragment = TrendingFragment()
                }
                R.id.nav_trending -> {
                    fragment = TrendingFragment()
                }
                R.id.nav_followers -> {
                    fragment = UserListFragment()
                }
            }
            fragmentList.put(itemId, fragment)
        }
        switchTo(fragment)
    }

}
