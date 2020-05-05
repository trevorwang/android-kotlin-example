package mingsin.github.model

/**
 * Created by trevorwang on 19/12/2016.
 */
data class User(var login: String,
                     var id: Int,
                     var avatarUrl: String,
                     var gravatarId: String,
                     var url: String,
                     var htmlUrl: String,
                     var followersUrl: String,
                     var followingUrl: String,
                     var gistsUrl: String,
                     var starredUrl: String,
                     var subscriptionsUrl: String,
                     var organizationUrl: String,
                     var reposUrl: String,
                     var eventsUrl: String,
                     var siteAdmin: String,
                     var type: String,
                     var receivedEventsUrl: String)