package mingsin.github.model

import java.util.*

/**
 * Created by trevorwang on 25/12/2016.
 */
data class UserDetail(var user: User,
                      var name: String,
                      var company: String,
                      var blog: String,
                      var location: String,
                      var email: String,
                      var hireable: String,
                      var bio: String,
                      var publicRepos: Int,
                      var publicGists: Int,
                      var followers: Int,
                      var following: Int,
                      var createdAt: Date,
                      var updatedAt: Date
)