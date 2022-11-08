package net.pantasystem.todoapp

import net.pantasystem.todoapp.domain.*
import net.pantasystem.todoapp.impl.AccountRepositoryImpl
import net.pantasystem.todoapp.impl.AuthRepositoryImpl
import net.pantasystem.todoapp.impl.TaskRepositoryImpl
import net.pantasystem.todoapp.repository.AccountRepository
import net.pantasystem.todoapp.repository.AuthRepository
import net.pantasystem.todoapp.repository.TaskRepository
import platform.Foundation.NSUserDefaults

class IOSModule {

    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl(
            NSUserDefaults.standardUserDefaults()
        )
    }

    fun provideAccountRepository(): AccountRepository {
        return AccountRepositoryImpl(provideAuthRepository(), provideApiProvider())
    }

    fun provideApiProvider(): ApiProvider {
        return ApiProvider()
    }

    fun provideTaskRepository(): TaskRepository {
        return TaskRepositoryImpl(provideAuthRepository(), provideApiProvider())
    }

    fun provideCreateTaskUseCase(): CreateTaskUseCase {
        return CreateTaskUseCase(provideTaskRepository())
    }

    fun provideLoadOneTaskUseCase(): LoadOneTaskUseCase {
        return LoadOneTaskUseCase(provideTaskRepository())
    }

    fun provideLoadTasksUseCase(): LoadTasksUseCase {
        return LoadTasksUseCase(provideTaskRepository())
    }

    fun provideToggleCompleteTaskUseCase(): ToggleCompleteTaskUseCase {
        return ToggleCompleteTaskUseCase(provideTaskRepository())
    }

    fun provideLoadAccountUseCase(): LoadAccountUseCase {
        return LoadAccountUseCase(provideAccountRepository())
    }
}