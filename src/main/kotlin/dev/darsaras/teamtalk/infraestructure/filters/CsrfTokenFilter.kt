package dev.darsaras.teamtalk.infraestructure.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.filter.OncePerRequestFilter

class CsrfTokenFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = request.getAttribute(CsrfToken::class.java.name) as CsrfToken
        token.token
        filterChain.doFilter(request,response)
    }

}