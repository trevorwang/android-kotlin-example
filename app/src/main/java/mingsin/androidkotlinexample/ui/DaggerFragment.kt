package mingsin.androidkotlinexample.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import mingsin.androidkotlinexample.di.DaggerFragmentComponent
import mingsin.androidkotlinexample.di.FragmentComponent

/**
 * Created by wangta on 2/17/17.
 * This fragment is only used as the fragment which attached to an activity
 */
abstract class DaggerFragment : Fragment() {
    var component: FragmentComponent? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val activity = activity as DaggerActivity
        component = DaggerFragmentComponent.builder()
                .activityComponent(activity.component)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInject()
    }

    abstract fun onInject()

    override fun onDetach() {
        component = null
        super.onDetach()
    }
}