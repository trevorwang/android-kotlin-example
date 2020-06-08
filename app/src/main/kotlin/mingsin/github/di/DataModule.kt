package mingsin.github.di

import dagger.Binds
import dagger.Module
import mingsin.github.repo.GithubRepository
import mingsin.github.repo.GithubRepositoryImpl
import mingsin.github.repo.local.LocalRepository
import mingsin.github.repo.local.LocalRepositoryImpl
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun githubRepo(repo: GithubRepositoryImpl): GithubRepository

    @Singleton
    @Binds
    abstract fun localRepo(repo: LocalRepositoryImpl): LocalRepository
}