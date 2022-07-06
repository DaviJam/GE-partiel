package eu.ensup.gestionEcole.utils;

import eu.ensup.gestionEcole.exceptions.NonValidJWTTokenException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.OffsetDateTimeType;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Function;

@Configuration
@Slf4j
public class JwtUtil {

    private Long tenMins = 1000 * 60 * 1L;
    private String secret = "toto";

    public Boolean validateToken(String token, UserDetails userDetails) throws NonValidJWTTokenException, NonValidJWTTokenException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException{
        final String username = extractUsername(token);
        return  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public <T>  T extractClaim(String token, Function<Claims, T> claimsResolver) throws NonValidJWTTokenException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException{
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) throws NonValidJWTTokenException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        return extractClaim(token, Claims::getSubject);
    }

    // PRIVATE METHODS
    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+tenMins))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) throws NonValidJWTTokenException{
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) throws NonValidJWTTokenException {
        return extractClaim(token, Claims::getExpiration);
    }

}
