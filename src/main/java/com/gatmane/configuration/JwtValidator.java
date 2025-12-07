package com.gatmane.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt != null && jwt.startsWith("Bearer ")) {

            String token = jwt.substring(7);

            try {

                // Key for verifying signature
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

                // Parse and verify the token
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();
                ;

                // Extract data
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));

                // Convert "ROLE_USER,ROLE_ADMIN" → List<GrantedAuthority>
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList
                        (authorities);


                // Create Authentication Object
                Authentication auth =
                        new UsernamePasswordAuthenticationToken(email, null, auths);

                // Store into SecurityContext
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
//                System.out.println("JWT validation failed: " + e.getMessage());

                new BadCredentialsException("Invalid JWT...");
            }
        }

        // Continue filter chain
        filterChain.doFilter(request, response);
    }
}
