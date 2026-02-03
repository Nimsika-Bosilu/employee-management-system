package edu.nimsika.ecom.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Data
@AllArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.contains("/auth/user-profile")) {
            log.info("Filter Hit for URL: " + path);
        }
         String jwtToken = null;
         String username = null;

        if(request.getCookies()!=null){
            for(Cookie cookie:request.getCookies()){
                if("accessToken".equals(cookie.getName())){
                    jwtToken=cookie.getValue();
                    if (path.contains("/auth/user-profile")) {
                        System.out.println(">>> Cookie FOUND: " + jwtToken.substring(0, 10) + "...");
                    }
                }
            }

        }else {
            if (path.contains("/auth/user-profile")) {
                log.info(">>> No Cookies Found in Request!");
            }
        }

        if(jwtToken==null){
            filterChain.doFilter(request,response);
            return;
        }
        try {
            username = jwtService.extractUsername(jwtToken);
            if (path.contains("/auth/user-profile")) {
                log.info("Extracted Username: " + username);
            }
        } catch (Exception e) {
            log.info("Error Extracting Username: " + e.getMessage());
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // 4. Token Validate කරමු
            boolean isValid = jwtService.validateToken(jwtToken, userDetails);
            if (path.contains("/auth/user-profile")) {
                log.info(">>> Token Valid?: " + isValid);
            }

            if (isValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("Authentication Set to Context!");
            } else {
                log.info("Token Validation FAILED!");
            }
        }
        filterChain.doFilter(request,response);
    }
}
