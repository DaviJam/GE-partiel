package eu.ensup.gestionEcole.controller;



import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import eu.ensup.gestionEcole.dto.TokenDto;
import eu.ensup.gestionEcole.dto.UserLoginDTO;
import eu.ensup.gestionEcole.service.CustomUserDetailsService;
import eu.ensup.gestionEcole.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import eu.ensup.gestionEcole.service.DirecteurService;
import eu.ensup.gestionEcole.service.EtudiantService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@CrossOrigin
public class HomeController {

    @Autowired
    DirecteurService directeurService;
    
    @Autowired
    EtudiantService etudiantService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/api/refreshtoken/{email}")
    public TokenDto refreshToken(@PathVariable String email){
        String token = jwtUtil.generateToken(email);
        Date expiryDate = jwtUtil.extractClaim(token, Claims::getExpiration);
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern( "yyyy/dd/MM HH:mm:ss" , Locale.FRENCH );
        OffsetDateTime date = OffsetDateTime.ofInstant(expiryDate.toInstant(), ZoneId.of("Europe/Paris"));
        String output = date.format( fOut );
        TokenDto tokenDto = new TokenDto(token, output);
        return tokenDto;
    }

    @PostMapping(value = "/login",consumes = "Application/json", produces = "Application/json")
    public TokenDto generateToken(@RequestBody UserLoginDTO userLoginDTO) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));

        String token = jwtUtil.generateToken(userLoginDTO.getEmail());
        Date expiryDate = jwtUtil.extractClaim(token, Claims::getExpiration);
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern( "yyyy/dd/MM HH:mm:ss" , Locale.FRENCH );
        OffsetDateTime date = OffsetDateTime.ofInstant(expiryDate.toInstant(), ZoneId.of("Europe/Paris"));
        String output = date.format( fOut );
        System.out.println(output);
        TokenDto tokenDto = new TokenDto(token, output);
//        Cookie tokenCookie = new Cookie("token", token);
//        tokenCookie.setPath("/products");
//        tokenCookie.setHttpOnly(true);
//        tokenCookie.setMaxAge((int) (jwtUtil.extractClaim(token, Claims::getExpiration).toInstant().getEpochSecond() - System.currentTimeMillis() / 1000));
//        res.addCookie(tokenCookie);
        //cookiesMap.put("Set-Cookie", List.of("token="+token+"; Secure; Domain=localhost:8081; HttpOnly; Max-Age=86400; Expires="+jwtUtil.extractClaim(token, Claims::getExpiration)));
        return tokenDto;
    }
}