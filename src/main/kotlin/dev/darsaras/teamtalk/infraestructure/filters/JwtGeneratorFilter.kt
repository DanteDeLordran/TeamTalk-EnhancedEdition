package dev.darsaras.teamtalk.infraestructure.filters

import dev.darsaras.teamtalk.application.services.user.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import java.util.*

private const val JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4"
private const val JWT_HEADER = "Authorization"

class JwtGeneratorFilter(private val userService: UserService) : OncePerRequestFilter() {

    private val expiration = (4 * 60 * 60 * 1000).toLong()

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val key = Keys.hmacShaKeyFor(JWT_KEY.toByteArray(StandardCharsets.UTF_8))
        val authentication = SecurityContextHolder.getContext().authentication
        val user = userService.getUserByEmail(authentication.name).body
        if (authentication != null){
            val token = Jwts.builder()
                .issuer("DanteDeLordran")
                .subject("BPCToken")
                .claim("email",user?.email)
                .claim("role", user?.role?.name ?: "ROLE_NONE")
                .issuedAt(Date())
                .expiration(Date(Date().time + expiration))
                .signWith(key)
                .compact()
            response.setHeader(JWT_HEADER, token)
        }
        filterChain.doFilter(request,response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return !request.servletPath.equals("/auth/login")
    }



}