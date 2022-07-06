package eu.ensup.gestionEcole.services;


import eu.ensup.gestionEcole.dao.CourseDao;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type Course dao tests.
 */
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CourseDaoTests {

    /**
     * The Course service.
     */
    @InjectMocks
    CourseService courseService;

    /**
     * The Course dao.
     */
    @Mock
    CourseDao courseDao;

    /**
     * Get all course.
     */
    @Test
    @DisplayName("Get all courses")
    void getAllCourse(){
        // get math course
        List<Cours> coursList = new ArrayList<>();
        coursList.add(Cours.builder().id(0L).theme("Math").nbHeures(15).build());
        coursList.add(Cours.builder().id(1L).theme("Java").nbHeures(15).build());
        coursList.add(Cours.builder().id(2L).theme("Cloud").nbHeures(15).build());
        coursList.add(Cours.builder().id(3L).theme("IT").nbHeures(15).build());
        coursList.add(Cours.builder().id(4L).theme("Angular").nbHeures(15).build());

        // stub dao
        when(courseDao.findAll()).thenReturn(coursList);

        // Get all courses
        List<Cours> courses = courseService.getallCourse();

        //find all from repo
        verify(courseDao).findAll();

        for(int i = 0; i< courses.size(); i++) {
            Assertions.assertEquals(courses.get(i).getId(), coursList.get(i).getId());
            Assertions.assertEquals(courses.get(i).getNbHeures(), coursList.get(i).getNbHeures());
            Assertions.assertEquals(courses.get(i).getTheme(), coursList.get(i).getTheme());
        }
    }
}
