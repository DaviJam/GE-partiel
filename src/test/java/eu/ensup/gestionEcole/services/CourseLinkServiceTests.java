package eu.ensup.gestionEcole.services;

import eu.ensup.gestionEcole.dao.CourseLinkDao;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.CourseLink;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.service.CourseLinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CourseLinkServiceTests {
    @InjectMocks
    private CourseLinkService courseLinkService;

    @Mock
    private CourseLinkDao courseLinkDao;

    // new student
    Etudiant student = new Etudiant(null,"UUID1","toto", "tito", "toto@email.com", "12 av titotu 78250 Yvi source", "0615485646",LocalDate.of(2000,05,25));

    // get math course
    Cours mathCourse = Cours.builder().id(0L).theme("Math").nbHeures(15).build();

    List<CourseLink> courseLinklist;
    @BeforeEach
    void setUp() {
        courseLinklist = new ArrayList<>();
        courseLinklist.add(CourseLink.builder().idStudent("UUID1").idCourse(0L).build());
        courseLinklist.add(CourseLink.builder().idStudent("UUID2").idCourse(1L).build());
        courseLinklist.add(CourseLink.builder().idStudent("UUID3").idCourse(0L).build());
        courseLinklist.add(CourseLink.builder().idStudent("UUID4").idCourse(1L).build());
        courseLinklist.add(CourseLink.builder().idStudent("UUID1").idCourse(1L).build());
    }

    @Test
    void linkStudent(){

        // stub dao
        CourseLink courseLink = new CourseLink(null, this.student.getUuid(), this.mathCourse.getId());
        when(courseLinkDao.save(any(CourseLink.class))).thenReturn(courseLink);

        // add student to course
        CourseLink courseLinkFromDao = this.courseLinkService.associate(this.student.getUuid(), this.mathCourse.getId());

        // verify
        Mockito.verify(courseLinkDao).save(any(CourseLink.class));

        // assertions
        Assertions.assertEquals(courseLinkFromDao.getIdStudent(), courseLink.getIdStudent());
        Assertions.assertEquals(courseLinkFromDao.getIdCourse(), courseLink.getIdCourse());
    }

    @Test
    void getAllLink(){

        // stub dao
        when(courseLinkDao.findAll()).thenReturn(courseLinklist);

        // add student to course
        List<CourseLink> links = this.courseLinkService.getAllLink();

        // verify
        Mockito.verify(courseLinkDao).findAll();

        // assertions
        Assertions.assertEquals(5, links.size() );
        links.forEach(courseLink -> Assertions.assertNotNull(courseLink));
    }

    @Test
    void deleteLinkofaStudent(){
        // stub dao
        Mockito.doNothing().when(courseLinkDao).deleteAllByIdStudent(any(String.class));
        courseLinkService.deleteCourseLink("UUID1");
        verify(courseLinkDao).deleteAllByIdStudent(any(String.class));
    }
}
