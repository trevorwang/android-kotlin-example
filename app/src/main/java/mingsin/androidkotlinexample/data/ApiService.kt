package mingsin.androidkotlinexample.data

import retrofit2.http.GET
import rx.Observable

/**
 * Created by Trevor Wang on 2/16/17.
 */

interface ApiService {
    @GET("/ip")
    fun ip(): Observable<Ip>
}