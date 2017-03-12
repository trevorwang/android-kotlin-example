package mingsin.androidkotlinexample.di

import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by Trevor Wang on 2/17/17.
 */

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
@Scope
annotation class ForFragment