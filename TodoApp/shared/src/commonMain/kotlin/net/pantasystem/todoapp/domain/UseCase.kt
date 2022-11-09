package net.pantasystem.todoapp.domain

interface UseCase0 {
    suspend operator fun invoke()
}
interface UseCase<T> {
    suspend operator fun invoke(args: T)
}

interface UseCaseReturns<T, R> {
    suspend operator fun invoke(args: T): R
}

interface UseCase0Returns<R> {
    suspend operator fun invoke(): R
}

suspend fun UseCase0.asResult(): Result<Unit> {
    return runCatching {
        invoke()
    }
}
suspend fun<T> UseCase<T>.asResult(args: T): Result<Unit> {
    return runCatching {
        invoke(args)
    }
}

suspend fun<T, R> UseCaseReturns<T, R>.asResult(args: T): Result<R> {
    return runCatching {
        invoke(args)
    }
}

suspend fun <R> UseCase0Returns<R>.asResult(): Result<R> {
    return runCatching {
        invoke()
    }
}