package eu.ensup.gestionEcole.services;

import eu.ensup.gestionEcole.dao.CourseLinkDao;
import eu.ensup.gestionEcole.dao.EtudiantDao;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.CourseLink;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.service.CourseLinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @BeforeEach
    void setUp() {
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
}
