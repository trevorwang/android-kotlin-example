package mingsin.github.data

import mingsin.github.model.Contributor
import mingsin.github.model.RepositoryResult
import mingsin.github.model.User
import mingsin.github.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by trevorwang on 08/12/2016.
 */
interface GithubApiService {

    @GET("repos/{owner}/{repo}/contributors")
    suspend fun contributors(
            @Path("owner") owner: String,
            @Path("repo") repo: String): List<Contributor>

    @GET("search/repositories")
    suspend fun trending(@Query("q") q: String,
                         @Query("sorts") sorts: String = "star",
                         @Query("order") order: String = " desc",
                         @Query("page") page: Int = 1): RepositoryResult

    @GET("search/repositories")
    suspend fun trendingKotlin(@Query("q") q: String,
                               @Query("sorts") sorts: String = "star",
                               @Query("order") order: String = " desc",
                               @Query("page") page: Int = 1): RepositoryResult

    @GET("users")
    suspend fun users(@Query("since") since: String = "0"): List<User>

    @GET("users/{username}")
    suspend fun userDetail(@Path("username") username: String): UserDetail

    @GET("user")
    suspend fun currentUser(): User

}