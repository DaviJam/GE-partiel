package eu.ensup.gestionEcole.filters;

import eu.ensup.gestionEcole.exceptions.NonValidJWTTokenException;
import eu.ensup.gestionEcole.exceptions.TokenExpiredException;
import eu.ensup.gestionEcole.service.CustomUserDetailsService;
import eu.ensup.gestionEcole.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;

    private CustomUserDetailsService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, TokenExpiredException, NonValidJWTTokenException {
        String authorizationHeader = request.getHeader("Authorization");
        String token=null;
        String namespace = "Bearer ";
        String userName = null;

        System.out.println(authorizationHeader);
        if(authorizationHeader != null && authorizationHeader.startsWith(namespace)){
            token = authorizationHeader.substring(namespace.length());
            try {
                userName = jwtUtil.extractUsername(token);
            } catch (Exception e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
               throw new NonValidJWTTokenException(e.getMessage());
            }
        }
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userService.loadUserByUsername(userName);
            try {
                if (jwtUtil.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (Exception e) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                throw new TokenExpiredException(e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
