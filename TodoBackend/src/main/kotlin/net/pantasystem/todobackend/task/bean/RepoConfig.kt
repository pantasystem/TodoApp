package net.pantasystem.todobackend.task.bean

import net.pantasystem.todobackend.task.TaskRepository
import net.pantasystem.todobackend.task.impl.TaskRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepoConfig {

    @Bean
    fun provideTaskRepository(): TaskRepository {
        return TaskRepositoryImpl()
    }
}