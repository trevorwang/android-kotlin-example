package mingsin.androidkotlinexample.di

import dagger.Component
import mingsin.androidkotlinexample.ExampleFragment

/**
 * Created by wangta on 2/17/17.
 */
@ForFragment
@Component(modules = arrayOf(FragmentModule::class),
        dependencies = arrayOf(ActivityComponent::class))
interface FragmentComponent {

    fun inject(fragment: ExampleFragment)
}