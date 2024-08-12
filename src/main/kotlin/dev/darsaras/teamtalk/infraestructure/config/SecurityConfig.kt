package dev.darsaras.teamtalk.infraestructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
class SecurityConfig {

    @Bean
    fun securityConfiguration( http : HttpSecurity ) : SecurityFilterChain {
        http {
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            csrf { disable() }
            cors {
                configurationSource = CorsConfigurationSource {
                    CorsConfiguration().apply {
                        allowedOrigins = listOf("*")
                        allowedHeaders = listOf("*")
                        allowedMethods = listOf(
                            HttpMethod.GET.name(),
                            HttpMethod.PUT.name(),
                            HttpMethod.POST.name(),
                            HttpMethod.PATCH.name(),
                            HttpMethod.DELETE.name()
                        )
                        allowCredentials = true
                        exposedHeaders = listOf("Authorization")
                        maxAge = 3600L
                    }
                }
            }
            authorizeRequests {
                authorize("/swagger-ui/**", permitAll)
                authorize("/v3/api-docs/**", permitAll)
                authorize("/swagger-resources/**", permitAll)
                authorize("/webjars/**", permitAll)
                authorize("/auth/**", authenticated)
                authorize(anyRequest, authenticated)
            }
            httpBasic {  }
            formLogin { disable() }
        }
        return http.build()
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}