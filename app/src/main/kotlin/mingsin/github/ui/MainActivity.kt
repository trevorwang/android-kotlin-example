package mingsin.github.ui


import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.net.ConnectivityManager
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import mingsin.github.R
import mingsin.github.databinding.ActivityMainBinding
import mingsin.github.databinding.NavHeaderMainBinding
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: androidx.drawerlayout.widget.DrawerLayout
    private val fragmentList: SparseArray<BaseFragment> = SparseArray()
    private val subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setSupportActionBar(binding.appBar?.toolbar)

        drawer = binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, binding.appBar?.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        val navBinding = NavHeaderMainBinding.bind(binding.navView.getHeaderView(0))
        navBinding.ivAvatar.setOnClickListener {
            val intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }
        selectDrawerMenuItem(R.id.nav_trending)
    }


    override fun onStop() {
        super.onStop()
        subscriptions.dispose()
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
//                R.id.nav_dashboard -> {
//                    fragment = DashboardFragment()
//                }
                R.id.nav_trending -> {
                    fragment = TrendingFragment()
                }
//                R.id.nav_user -> {
//                    fragment = UserFragment()
//                }
            }
        }
        switchTo(fragment)
        fragmentList.put(itemId, fragment)
    }

}
