package dev.darsaras.teamtalk.infraestructure.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class JwtValidatorFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        TODO("Not yet implemented")
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath.equals("/auth/login")
    }

}