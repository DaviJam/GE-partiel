package eu.ensup.gestionEcole.controller;



import java.time.LocalDate;
import java.util.List;

import eu.ensup.gestionEcole.domain.Directeur;
import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import eu.ensup.gestionEcole.service.DirecteurService;
import eu.ensup.gestionEcole.service.EtudiantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class HomeControllerImpl implements HomeController {

    @Autowired
    DirecteurService directeurService;
    
    @Autowired
    EtudiantService etudiantService;


    /**
     * redirect to Home page with thymeleaf
     * @return String
     */
    @Override
    @GetMapping("/")
    public String viewHome() {
        return "home";
    }

    
    //    /**
    //     * redirect to login page with thymeleaf
    //     * @return String
    //     */
    //    @Override
    //    @GetMapping("/login")
    //
    //    public String login() {
    //        return "login";
    //    }


    /** 
     * redirect to with page for "Etudiant" creation with thymeleaf
     * @param model model object used by thymleaf
     * @return String
    */
    @GetMapping("/creationEtudiant")
    public String creationEtudiant(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "creationEtudiant";
    }

    /**
     * handler method to get data from "Etudiant" creation page to create the "Etudiant"
     * 
     * @param etudiant etudiant info you want to create
     * @param model model object used by thymleaf
     * @return String
     */
    @PostMapping("/perfom_creationEtudiant")
    public String creationEtudiantPost(@ModelAttribute Etudiant etudiant, Model model){
        etudiantService.createStudent(etudiant);
        return "/etudiants";
    }

    /**
     * Endpoint to create a "Directeur" and rediredt to Home Page
     * @return String
     */
    @GetMapping("/addDirecteurtmp")
    public Directeur addDirecteurtmp() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("directeur");
        Directeur directeur = new Directeur(null, "directeur@ensup.eu", encodedPassword);

        return directeurService.createDirecteur(directeur);
    }

    /**
     * Endpoint to create a "Responsable" and rediredt to Home Page
     * @return String
     */
//    @GetMapping("/addResponsableTmp")
//    public String addResponsableTmp() {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode("123");
//        Responsable directeur = new Responsable("Lacomblez", "Thomas", "Lacomblez.thomas@gmail.com", encodedPassword , "1 Sq. Benjamin Franklin, 78180", "0634779411", LocalDate.of(1999, 8, 22));
//
//        //directeurService(directeur);
//        log.info("Directeur créer ! ");
//        return "home";
//    }


    /**
     * Endpoint to create a "Etudiant" and rediredt to Home Page
     * @return String
     */
    @GetMapping("/addEtudianttmp")
    public Etudiant addEtudianttmp() {
        Etudiant etudiant = new Etudiant(null, "0", "nom", "prenom", "email@email.eu", "mon adresse", "tel", LocalDate.of(1999, 8, 22));

        return etudiantService.createStudent(etudiant);
    }


    /**
     * redirect to the page that list all the "Etudiant" that exists with thymleaf
     * @param model model object used by thymleaf
     * @return String
     */
    @GetMapping("/etudiants")
    public String listEtudiants(Model model) {
        List<Etudiant> listEtudiant = etudiantService.getallStudent();
        model.addAttribute("listEtudiant", listEtudiant);
        
        return "etudiants";
    }

}