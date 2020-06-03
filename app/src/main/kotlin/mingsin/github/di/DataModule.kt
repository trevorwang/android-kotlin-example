package mingsin.github.di

import dagger.Binds
import dagger.Module
import mingsin.github.repo.GithubRepository
import mingsin.github.repo.GithubRepositoryImpl
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun githubRepo(repo: GithubRepositoryImpl): GithubRepository
}