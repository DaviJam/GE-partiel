package eu.ensup.gestionEcole.filters;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            System.out.println("######################");
            switch(HttpStatus.valueOf(response.getStatus())){
                case FORBIDDEN:
                    logger.error("Forbidden", e);
                    response.getWriter().write("Contact you administrator to get a valid account to login");
                    break;
                case UNAUTHORIZED:
                    logger.error("Unauthorized");
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized ! Please, try login before.");
                    break;
                default:
                    response.getWriter().write("An error occurred");
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    break;
            }
        }
    }
}