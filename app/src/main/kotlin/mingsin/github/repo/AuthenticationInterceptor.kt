package mingsin.github.repo

import com.orhanobut.logger.Logger
import kotlinx.coroutines.runBlocking
import mingsin.github.repo.local.LocalRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationInterceptor @Inject constructor(val repo: LocalRepository) : Interceptor {
    lateinit var token: String
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!::token.isInitialized) {
            token = runBlocking {
                repo.getAccessToken() ?: "PLEASE REPLACE WITH YOUR PERSONAL ACCESS TOKEN"
            }
        }
        val newRequest = chain.request().newBuilder()
                .header("Authorization", "token $token")
                .build()

        return chain.proceed(newRequest)
    }
}