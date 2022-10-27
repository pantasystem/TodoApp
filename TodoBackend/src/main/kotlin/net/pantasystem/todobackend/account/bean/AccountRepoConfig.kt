package net.pantasystem.todobackend.account.bean

import net.pantasystem.todobackend.account.AccountRepository
import net.pantasystem.todobackend.account.impl.AccountRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AccountRepoConfig {
    @Bean
    fun provideAccountRepository(): AccountRepository {
        return AccountRepositoryImpl()
    }
}