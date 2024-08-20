package dev.darsaras.teamtalk.infraestructure.filters

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

private const val JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4"
private const val JWT_HEADER = "Authorization"

class JwtValidatorFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val key = Keys.hmacShaKeyFor(JWT_KEY.toByteArray(StandardCharsets.UTF_8))
        val token = request.getHeader(JWT_HEADER)
        if ( token != null ){
            try {
                val claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .payload
                val email = "${claims["email"]}"
                val role = "${claims["role"]}"
                val authentication = UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.commaSeparatedStringToAuthorityList(role))
                SecurityContextHolder.getContext().authentication = authentication
            }catch ( e : ExpiredJwtException ){
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.write("Token has expired $e")
            }catch ( e : JwtException ){
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.write("Invalid token $e")
            }
        }
        filterChain.doFilter(request,response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath.equals("/auth/login")
    }

}