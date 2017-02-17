package mingsin.androidkotlinexample.di

import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by wangta on 2/16/17.
 */
@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
@Scope
annotation class ForActivity