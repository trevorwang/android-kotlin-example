package mingsin.androidkotlinexample.di

import android.app.ProgressDialog
import dagger.Module
import dagger.Provides
import mingsin.androidkotlinexample.ui.DaggerActivity
import mingsin.androidkotlinexample.R

/**
 * Created by wangta on 2/16/17.
 */
@Module
class ActivityModule(val activity: DaggerActivity) {

    @Provides
    fun activity(): DaggerActivity {
        return activity
    }

    @Provides
    fun progressDialog(): ProgressDialog {
        val progress = ProgressDialog(activity)
        progress.setMessage(activity.getString(R.string.loading))
        return progress
    }
}