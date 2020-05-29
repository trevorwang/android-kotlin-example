package mingsin.github.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by trevorwang on 08/12/2016.
 */
@Singleton
class RestApi @Inject constructor() {
    val clientId = "6e4e8221c0dcbd375140"
    val clientSecret = "aadd49231fea44e5e1302cedcbfa757013b20516"
    val githubApi = "https://api.github.com/"

    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .baseUrl(githubApi)
                .build()
    }

    private fun createGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    }
}