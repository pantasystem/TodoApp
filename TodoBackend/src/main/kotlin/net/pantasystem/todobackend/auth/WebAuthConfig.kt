package net.pantasystem.todobackend.auth


import net.pantasystem.todobackend.account.Accounts
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.web.method.HandlerMethod
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class WebAuthConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(object : HandlerInterceptor {
            override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
                val method = (handler as? HandlerMethod)?.method
                if (method != null && AnnotationUtils.findAnnotation(method, NonAuthorize::class.java) != null) {
                    return true
                }

                val controller = method?.declaringClass
                if ((controller != null
                    && AnnotationUtils.findAnnotation(controller, Authorize::class.java) != null)
                    || (method != null && AnnotationUtils.findAnnotation(method, Authorize::class.java) != null)
                ) {
                    val authorizationHeader = request.getHeader("Authorization")
                    if (authorizationHeader.isNullOrBlank()) {
                        throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
                    }
                    val token = authorizationHeader.getBearerToken()
                    if (token.isNullOrBlank()) {
                        throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
                    }
                    return transaction {
                        Accounts.select( Accounts.token eq token).limit(1).firstOrNull() != null
                    }

                }

                return true
            }
        })
    }
}
