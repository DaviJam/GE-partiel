package eu.ensup.gestionEcole.controller;

import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseAPI {
    @Autowired
    CourseService courseService;

    @GetMapping("")
    public List<Cours> getallCourse(){ return courseService.getallCourse();}
}
