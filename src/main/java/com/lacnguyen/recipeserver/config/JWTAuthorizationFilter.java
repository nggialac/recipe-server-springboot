package com.lacnguyen.recipeserver.config;

import io.jsonwebtoken.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

        private final String HEADER = "Authorization";
        private final String PREFIX = "Bearer ";
        private final String SECRET = "mySecretKey";

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
            try {
                System.out.println("doFilterInternal: "+request);
                System.out.println("doFilterInternal: "+chain);

                if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                    response.setStatus(HttpServletResponse.SC_OK);
                }

                if (checkJWTToken(request, response)) {
                    Claims claims = validateToken(request);
                    if (claims.get("authorities") != null) {
                        setUpSpringAuthentication(claims);
                    } else {
                        SecurityContextHolder.clearContext();
                    }
                }else {
                    SecurityContextHolder.clearContext();
                }
                chain.doFilter(request, response);
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
                return;
            }
        }

        private Claims validateToken(HttpServletRequest request) {
            System.out.println("validateToken: ");
            String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
            return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
        }

        /**
         * Authentication method in Spring flow
         *
         * @param claims
         */
        private void setUpSpringAuthentication(Claims claims) {
            System.out.println("setUpSpringAuthentication");
            @SuppressWarnings("unchecked")
            List<String> authorities = (List) claims.get("authorities");

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                    authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
            System.out.println("checkJWTToken: " + request.getHeader(HEADER));

            String authenticationHeader = request.getHeader(HEADER);
            if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
                return false;
            return true;
    }
}
