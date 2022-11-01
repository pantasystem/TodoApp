package net.pantasystem.todoandroidexample.impl.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.pantasystem.todoandroidexample.Constants
import net.pantasystem.todoandroidexample.domain.AccountRepository
import net.pantasystem.todoandroidexample.domain.AuthRepository
import net.pantasystem.todoandroidexample.domain.TaskRepository
import net.pantasystem.todoandroidexample.impl.AccountRepositoryImpl
import net.pantasystem.todoandroidexample.impl.AuthRepositoryImpl
import net.pantasystem.todoandroidexample.impl.TaskRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryBindModule {

    @Singleton
    @Binds
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository

    @Singleton
    @Binds
    abstract fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository

}

@InstallIn(SingletonComponent::class)
@Module
object RepositoryProvideModule {

    @Provides
    @Singleton
    fun provideAuthRepository(@ApplicationContext context: Context): AuthRepository {
        return AuthRepositoryImpl(
            context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        )
    }
}