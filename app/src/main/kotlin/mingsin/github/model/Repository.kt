package mingsin.github.model

import java.util.*

/**
 * Created by trevorwang on 17/12/2016.
 */
data class Repository(var id: Int,
                      var name: String,
                      var owner: User,
                      var private: Boolean,
                      var htmlUrl: String,
                      var description: String,
                      var fork: Boolean,
                      var createdAt: Date,
                      var updatedAt: Date,
                      var pushedAt: Date,
                      var homepage: String,
                      var size: String,
                      var language: String,
                      var forksCount: Int,
                      var watchersCount: Int,
                      var stargazersCount: Int,
                      var openIssuesCount: Int,
                      var masterBranch: String,
                      var defaultBranch: String,
                      var score: Double,
                      var fullName: String)
