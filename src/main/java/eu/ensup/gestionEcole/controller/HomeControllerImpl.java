package eu.ensup.gestionEcole.controller;



import java.time.LocalDate;
import java.util.List;

import eu.ensup.gestionEcole.domain.Directeur;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.dto.UserLoginDTO;
import eu.ensup.gestionEcole.service.CustomUserDetailsService;
import eu.ensup.gestionEcole.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import eu.ensup.gestionEcole.service.DirecteurService;
import eu.ensup.gestionEcole.service.EtudiantService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@CrossOrigin
public class HomeControllerImpl implements HomeController {

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

    /**
     * redirect to Home page with thymeleaf
     * @return String
     */
    @Override
    @GetMapping("/")
    public String viewHome() {
        return "home";
    }


    @PostMapping(value = "/login",consumes = "Application/json", produces = "text/plain")
    public HttpEntity<String> generateToken(@RequestBody UserLoginDTO userLoginDTO) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));

        String token = jwtUtil.generateToken(userLoginDTO.getEmail());
//        Cookie tokenCookie = new Cookie("token", token);
//        tokenCookie.setPath("/products");
//        tokenCookie.setHttpOnly(true);
//        tokenCookie.setMaxAge((int) (jwtUtil.extractClaim(token, Claims::getExpiration).toInstant().getEpochSecond() - System.currentTimeMillis() / 1000));
//        res.addCookie(tokenCookie);
        //cookiesMap.put("Set-Cookie", List.of("token="+token+"; Secure; Domain=localhost:8081; HttpOnly; Max-Age=86400; Expires="+jwtUtil.extractClaim(token, Claims::getExpiration)));
        return new HttpEntity<String>(token);
    }
}