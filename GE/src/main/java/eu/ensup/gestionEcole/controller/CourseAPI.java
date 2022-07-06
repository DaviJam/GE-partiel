package eu.ensup.gestionEcole.controller;

import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * The type Course api.
 */
@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseAPI {
    /**
     * The Course service.
     */
    @Autowired
    CourseService courseService;

    /**
     * Getall course list.
     *
     * @return the list
     */
    @GetMapping("")
    public List<Cours> getallCourse(){ return courseService.getallCourse();}
}
