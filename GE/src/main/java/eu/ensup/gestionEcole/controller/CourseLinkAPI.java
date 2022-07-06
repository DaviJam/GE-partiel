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


/**
 * The type Course link api.
 */
@RestController
@RequestMapping("/api/link")
@CrossOrigin
public class CourseLinkAPI {

    /**
     * The Course link service.
     */
    @Autowired
    CourseLinkService courseLinkService;

    /**
     * Link course course link.
     *
     * @param linkCourseDTO the link course dto
     * @return the course link
     */
    @PostMapping("")
    public CourseLink linkCourse(@RequestBody LinkCourseDTO linkCourseDTO) {
        return courseLinkService.associate(linkCourseDTO.getIdStudent(), linkCourseDTO.getIdCourse());
    }

    /**
     * Gets courseof student.
     *
     * @param uuid the uuid
     * @return the courseof student
     */
    @GetMapping("/{uuid}")
    public List<CourseLink> getCourseofStudent(@PathVariable String uuid) {
        return courseLinkService.getCourseofStudent(uuid);
    }

    /**
     * Gets all link.
     *
     * @return the all link
     */
    @GetMapping("/getall")
    public List<CourseLink> getAllLink() {
        return courseLinkService.getAllLink();
    }
}
