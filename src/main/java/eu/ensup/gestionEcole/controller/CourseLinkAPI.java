package eu.ensup.gestionEcole.controller;

import eu.ensup.gestionEcole.domain.CourseLink;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.service.CourseLinkService;
import eu.ensup.gestionEcole.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseLinkAPI {

    @Autowired
    CourseLinkService courseLinkService;

    @GetMapping("/link/{uuid}&{courseid}")
    public CourseLink linkCourse(@PathVariable String uuid,@PathVariable Long courseid) {
        return courseLinkService.associate(uuid, courseid);
    }
}
