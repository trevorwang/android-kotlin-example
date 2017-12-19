package mingsin.androidkotlinexample.data

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Trevor Wang on 2/16/17.
 */

interface ApiService {
    @GET("/ip")
    fun ip(): Observable<Ip>
}