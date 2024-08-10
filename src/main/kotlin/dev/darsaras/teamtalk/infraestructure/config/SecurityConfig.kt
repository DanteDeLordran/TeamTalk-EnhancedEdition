package dev.darsaras.teamtalk.infraestructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityConfiguration( http : HttpSecurity ) : SecurityFilterChain {
        http.authorizeHttpRequests {
            r -> r.anyRequest().permitAll()
        }
        http.formLogin { Customizer.withDefaults<Any>() }
        http.httpBasic { Customizer.withDefaults<Any>() }
        return http.build()
    }

}