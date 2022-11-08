package net.pantasystem.todoapp.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.pantasystem.todoapp.ApiProvider
import net.pantasystem.todoapp.repository.AccountRepository
import net.pantasystem.todoapp.repository.AuthRepository
import net.pantasystem.todoapp.repository.TaskRepository
import net.pantasystem.todoapp.impl.AccountRepositoryImpl
import net.pantasystem.todoapp.impl.AuthRepositoryImpl
import net.pantasystem.todoapp.impl.TaskRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryProviderModule {

    @Provides
    @Singleton
    fun provideApiProvider(): ApiProvider {
        return ApiProvider()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        authRepository: AuthRepository,
        apiProvider: ApiProvider,
    ): TaskRepository {
        return TaskRepositoryImpl(authRepository,apiProvider)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        @ApplicationContext context: Context
    ): AuthRepository {
        val sharedPref = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        return AuthRepositoryImpl(sharedPref)
    }

    @Provides
    @Singleton
    fun provideAccountRepository(
        authRepository: AuthRepository,
        apiProvider: ApiProvider,
    ): AccountRepository {
        return AccountRepositoryImpl(authRepository, apiProvider)
    }


}