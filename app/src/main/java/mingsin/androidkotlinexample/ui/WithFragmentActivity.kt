package mingsin.androidkotlinexample.ui

import android.os.Bundle
import mingsin.androidkotlinexample.R

/**
 * Created by wangta on 2/17/17.
 */
class WithFragmentActivity : DaggerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_fragment)
    }
    override fun onInject() {
        component?.inject(this)
    }
}