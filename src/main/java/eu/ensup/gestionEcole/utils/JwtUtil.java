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

/**
 * The type Jwt util.
 */
@Configuration
@Slf4j
public class JwtUtil {

    private Long tenMins = 1000 * 60 * 15L;
    private String secret = "toto";

    /**
     * Validate token boolean.
     *
     * @param token       the token
     * @param userDetails the user details
     * @return the boolean
     * @throws NonValidJWTTokenException the non valid jwt token exception
     * @throws NonValidJWTTokenException the non valid jwt token exception
     * @throws ExpiredJwtException       the expired jwt exception
     * @throws UnsupportedJwtException   the unsupported jwt exception
     * @throws MalformedJwtException     the malformed jwt exception
     * @throws SignatureException        the signature exception
     * @throws IllegalArgumentException  the illegal argument exception
     */
    public Boolean validateToken(String token, UserDetails userDetails) throws NonValidJWTTokenException, NonValidJWTTokenException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException{
        final String username = extractUsername(token);
        return  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Generate token string.
     *
     * @param username the username
     * @return the string
     */
    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Extract claim t.
     *
     * @param <T>            the type parameter
     * @param token          the token
     * @param claimsResolver the claims resolver
     * @return the t
     * @throws NonValidJWTTokenException the non valid jwt token exception
     * @throws ExpiredJwtException       the expired jwt exception
     * @throws UnsupportedJwtException   the unsupported jwt exception
     * @throws MalformedJwtException     the malformed jwt exception
     * @throws SignatureException        the signature exception
     * @throws IllegalArgumentException  the illegal argument exception
     */
    public <T>  T extractClaim(String token, Function<Claims, T> claimsResolver) throws NonValidJWTTokenException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException{
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract username string.
     *
     * @param token the token
     * @return the string
     * @throws NonValidJWTTokenException the non valid jwt token exception
     * @throws ExpiredJwtException       the expired jwt exception
     * @throws UnsupportedJwtException   the unsupported jwt exception
     * @throws MalformedJwtException     the malformed jwt exception
     * @throws SignatureException        the signature exception
     * @throws IllegalArgumentException  the illegal argument exception
     */
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
