package mingsin.androidkotlinexample.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import mingsin.androidkotlinexample.R

/**
 * Created by Trevor Wang on 2/17/17.
 */
class WithFragmentActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_fragment)
    }
}