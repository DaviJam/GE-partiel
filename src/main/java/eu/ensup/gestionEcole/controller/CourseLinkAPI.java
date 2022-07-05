package eu.ensup.gestionEcole.controller;

import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.CourseLink;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.dto.LinkCourseDTO;
import eu.ensup.gestionEcole.service.CourseLinkService;
import eu.ensup.gestionEcole.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/link")
@CrossOrigin(origins = {"*"})
public class CourseLinkAPI {

    @Autowired
    CourseLinkService courseLinkService;

    @PostMapping("")
    public CourseLink linkCourse(@RequestBody LinkCourseDTO linkCourseDTO) {
        return courseLinkService.associate(linkCourseDTO.getIdStudent(), linkCourseDTO.getIdCourse());
    }

    @GetMapping("/{uuid}")
    public List<CourseLink> getCourseofStudent(@PathVariable String uuid) {
        return courseLinkService.getCourseofStudent(uuid);
    }

    @GetMapping("/getall")
    public List<CourseLink> getAllLink() {
        return courseLinkService.getAllLink();
    }
}
