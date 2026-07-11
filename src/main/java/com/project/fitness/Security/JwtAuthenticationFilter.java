package com.project.fitness.Security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, java.io.IOException {
        System.out.println("Filtering request...");
        try{       String jwt = jwtUtils.getJwtFromHeader(request);

        if (jwt != null && jwtUtils.validateJwtToken(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
            String userId = jwtUtils.getUserIdFromTheToken(jwt);
                        UserDetails userDetails = customUserDetailsService.loadUserById(userId);



                // claim from the jwtUtils


            Claims claims = jwtUtils.getAllClaims(jwt);
            List<String> roles = claims.get("roles", List.class);
            System.out.println(claims.get("roles"));

            List<GrantedAuthority> authorities = List.of();

            if (roles != null) {
                authorities = roles
                        .stream()
                        .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role))
                        .toList();
            }



            // Authentication

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }}

        catch( Exception e){
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}
