package eu.ensup.gestionEcole.filters;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyCORSFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println(request.getMethod());
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.addHeader("Access-Control-Allow-Headers", "remember-me, Content-Type, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
//        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Headers",
                "DNT,"+
                "X-CustomHeader,"+
                "Keep-Alive,"+
                "User-Agent,"+
                "X-Requested-With,"+
                "If-Modified-Since,"+
                "Cache-Control,"+
                "Content-Type,"+
                "Content-Range,"+
                "Range");
        response.addHeader("Access-Control-Allow-Credentials","true");

        response.addHeader("Access-Control-Request-Headers", "Authorization");
        if (request.getMethod().equals(HttpMethod.OPTIONS)) {
            System.out.println("OPTION HEADER RECEIVED");
            response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
            response.addHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Max-Age", "1728000");
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setHeader("Access-Control-Expose-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
        }

        filterChain.doFilter(request, response);
    }
}