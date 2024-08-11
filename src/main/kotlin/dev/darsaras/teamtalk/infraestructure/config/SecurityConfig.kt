package dev.darsaras.teamtalk.infraestructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityConfiguration( http : HttpSecurity ) : SecurityFilterChain {
        http {
            csrf {  }
            cors {  }
            securityMatcher("/api/**")
            authorizeRequests {
                authorize("/swagger-ui/**", permitAll)
                authorize(anyRequest,authenticated)
            }
            httpBasic {  }
            formLogin {  }
        }
        return http.build()
    }

}