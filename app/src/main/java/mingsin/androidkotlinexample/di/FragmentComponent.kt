package mingsin.androidkotlinexample.di

import dagger.Component
import mingsin.androidkotlinexample.ui.ExampleFragment

/**
 * Created by Trevor Wang on 2/17/17.
 */
@ForFragment
@Component(modules = arrayOf(FragmentModule::class),
        dependencies = arrayOf(ActivityComponent::class))
interface FragmentComponent {

    fun inject(fragment: ExampleFragment)
}