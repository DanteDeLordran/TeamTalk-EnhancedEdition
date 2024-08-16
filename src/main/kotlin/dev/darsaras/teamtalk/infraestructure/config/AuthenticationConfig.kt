package dev.darsaras.teamtalk.infraestructure.config

import dev.darsaras.teamtalk.application.services.user.UserService
import dev.darsaras.teamtalk.domain.models.role.Role
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class AuthenticationConfig( private val userService: UserService, private val passwordEncoder: PasswordEncoder) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val email = authentication.name
        val password = authentication.credentials.toString()
        val userPassword = userService.getUserPassword(email)
        val user = userService.getUserByEmail(email).body ?: throw Exception("Couldn't get user")
        if (passwordEncoder.matches(password, userPassword)){
            return UsernamePasswordAuthenticationToken(email, password , getGrantedAuthorities(user.role))
        }else throw BadCredentialsException("Invalid password")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return (UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication))
    }

    private fun getGrantedAuthorities(role: Role): List<GrantedAuthority> {
        val grantedAuthorities: MutableList<GrantedAuthority> = ArrayList()
        grantedAuthorities.add(SimpleGrantedAuthority(role.name))
        return grantedAuthorities
    }

}