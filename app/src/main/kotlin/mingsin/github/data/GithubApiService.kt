package mingsin.github.data

import io.reactivex.Observable
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
    fun contributors(
            @Path("owner") owner: String,
            @Path("repo") repo: String): Observable<List<Contributor>>

    @GET("search/repositories")
    fun trending(@Query("q") q: String,
                 @Query("sorts") sorts: String = "star",
                 @Query("order") order: String = " desc",
                 @Query("page") page: Int = 1): Observable<RepositoryResult>


    @GET("users")
    fun users(@Query("since") since:String = "0"): Observable<List<User>>

    @GET("users/{username}")
    fun userDetail(@Path("username") username: String): Observable<UserDetail>
}