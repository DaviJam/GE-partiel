package eu.ensup.gestionEcole.integration;

import eu.ensup.gestionEcole.controller.CourseAPI;
import eu.ensup.gestionEcole.controller.CourseLinkAPI;
import eu.ensup.gestionEcole.controller.EtudiantAPI;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.CourseLink;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.dto.LinkCourseDTO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTests {

    @Autowired
    protected EtudiantAPI etudiantAPI;

    @Autowired
    protected CourseAPI courseAPI;

    @Autowired
    protected CourseLinkAPI courseLinkAPI;

    private List<Etudiant> students;
    private List<Cours> courses;

    @BeforeEach
    void setup(){
        students = new ArrayList<>();
        students.add(0, new Etudiant(null, "UUID1","Ali", "Gator","ali.gator@gmail.com", "2 rue des animaux sauvages", "0761615263", LocalDate.now()));
        students.add(1, new Etudiant(null, "UUID2","Andy", "Capé","andy.cape@gmail.com", "2 rue des fauteuils", "0751624591", LocalDate.now()));
        students.add(2, new Etudiant(null, "UUID3","titi", "jamii","etudiant3@gmail.com", "adresse3", "telephone3", LocalDate.now()));
        students.add(3, new Etudiant(null, "UUID4","tutu", "jamiu","etudiant4@gmail.com", "adresse4", "telephone4", LocalDate.now()));

        courses = new ArrayList<>();
        courses.add(Cours.builder().id(0L).theme("Math").nbHeures(15).build());
        courses.add(Cours.builder().id(1L).theme("Java").nbHeures(15).build());
        courses.add(Cours.builder().id(2L).theme("Cloud").nbHeures(15).build());
        courses.add(Cours.builder().id(3L).theme("IT").nbHeures(15).build());
        courses.add(Cours.builder().id(4L).theme("Angular").nbHeures(15).build());
    }


    @SneakyThrows
    @Test
    @DisplayName("add a student")
    @Order(1)
    void addStudent(){
        Etudiant res = students.get(0);
        res.setId(null);
        res.setUuid("UUID5");
        res.setEmail("toto@email.com");

        Etudiant addedStudent = etudiantAPI.addStudent(res);

        // Mutable state => res is changed, the methods addStudent does not do a copy of res, its uses the same object

        Assertions.assertNotNull(addedStudent);
        Assertions.assertEquals(addedStudent.getId()            ,res.getId());
        Assertions.assertEquals(addedStudent.getUuid()          ,res.getUuid());
        Assertions.assertEquals(addedStudent.getNom()           ,res.getNom());
        Assertions.assertEquals(addedStudent.getPrenom()        ,res.getPrenom());
        Assertions.assertEquals(addedStudent.getEmail()         ,res.getEmail());
        Assertions.assertEquals(addedStudent.getDateNaissance() ,res.getDateNaissance());
        Assertions.assertEquals(addedStudent.getTelephone()     ,res.getTelephone());

        Assertions.assertNotNull(etudiantAPI.getStudent(addedStudent.getUuid()));
        Assertions.assertEquals(students.size(), 4);

    }

    @SneakyThrows
    @Test
    @DisplayName("Get one student")
    @Order(2)
    void getStudent(){
        Etudiant newStudent = etudiantAPI.addStudent(new Etudiant(null, "UUID1","Ali", "Gator","ali.gator@gmail.com", "2 rue des animaux sauvages", "0761615263", LocalDate.now()));
        Etudiant res = etudiantAPI.getStudent("UUID1");
        Assertions.assertNotNull(res);
        Assertions.assertNotEquals(newStudent.getId()         ,res.getId());
        Assertions.assertNotEquals(newStudent.getUuid()          ,res.getUuid());
        Assertions.assertEquals(newStudent.getNom()           ,res.getNom());
        Assertions.assertEquals(newStudent.getPrenom()        ,res.getPrenom());
        Assertions.assertEquals(newStudent.getEmail()         ,res.getEmail());
        Assertions.assertEquals(newStudent.getDateNaissance() ,res.getDateNaissance());
        Assertions.assertEquals(newStudent.getTelephone()     ,res.getTelephone());
    }

    @SneakyThrows
    @Test
    @DisplayName("Get all students")
    @Order(3)
    void getAllStudents(){
        List<Etudiant> res = etudiantAPI.getallStudents();
        Assertions.assertEquals(6, res.size());

        for(int i = 0; i < students.size(); i++){
            Assertions.assertNotEquals(students.get(i).getId(), res.get(i).getId());
            Assertions.assertEquals(students.get(i).getUuid(), res.get(i).getUuid());
            Assertions.assertEquals(students.get(i).getNom(), res.get(i).getNom());
            Assertions.assertEquals(students.get(i).getPrenom(), res.get(i).getPrenom());
            Assertions.assertEquals(students.get(i).getEmail(), res.get(i).getEmail());
            Assertions.assertEquals(students.get(i).getDateNaissance(), res.get(i).getDateNaissance());
            Assertions.assertEquals(students.get(i).getTelephone(), res.get(i).getTelephone());
        }
    }

    @SneakyThrows
    @Test
    @DisplayName("update a student")
    @Order(4)
    void updateStudent(){
        Etudiant res = students.get(0);
        res.setId(1L);
        res.setUuid("UUID1");
        res.setEmail("toto@email.com");

        Etudiant updatedStudent = etudiantAPI.updateStudent(res);

        // Mutable state => res is changed, the methods addStudent does not do a copy of res, its uses the same object

        Assertions.assertNotNull(updatedStudent);
        Assertions.assertEquals(updatedStudent.getId()            ,res.getId());
        Assertions.assertEquals(updatedStudent.getUuid()          ,res.getUuid());
        Assertions.assertEquals(updatedStudent.getNom()           ,res.getNom());
        Assertions.assertEquals(updatedStudent.getPrenom()        ,res.getPrenom());
        Assertions.assertEquals(updatedStudent.getEmail()         ,res.getEmail());
        Assertions.assertEquals(updatedStudent.getDateNaissance() ,res.getDateNaissance());
        Assertions.assertEquals(updatedStudent.getTelephone()     ,res.getTelephone());

        Assertions.assertNotNull(etudiantAPI.getStudent(updatedStudent.getUuid()));
        Assertions.assertEquals(students.size(), 4);
    }

    @SneakyThrows
    @Test
    @DisplayName("Delete a student")
    @Order(5)
    void deleteStudent(){
        Etudiant res = students.get(0);
        // delete user
        etudiantAPI.deleteStudent(res.getUuid());
        // try to get user in db
        Etudiant nullStudent = etudiantAPI.getStudent(res.getUuid());
        Assertions.assertNull(nullStudent);
    }

    @Test
    @DisplayName("Get all courses")
    @Order(6)
    void getAllCourse(){
        // get all courses
        List<Cours> allCourses = courseAPI.getallCourse();
        // check
        Assertions.assertEquals(allCourses.size(), 4);

        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getId() == courses.get(0).getId()));
        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getId() == courses.get(1).getId()));
        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getId() == courses.get(2).getId()));
        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getId() == courses.get(3).getId()));

        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getTheme().compareTo(courses.get(0).getTheme())==0));
        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getTheme().compareTo(courses.get(1).getTheme())==0));
        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getTheme().compareTo(courses.get(2).getTheme())==0));
        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getTheme().compareTo(courses.get(3).getTheme())==0));

        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getNbHeures() == courses.get(0).getNbHeures()));
        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getNbHeures() == courses.get(1).getNbHeures()));
        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getNbHeures() == courses.get(2).getNbHeures()));
        Assertions.assertNotNull(allCourses.stream().filter(cours -> cours.getNbHeures() == courses.get(3).getNbHeures()));
    }

    @SneakyThrows
    @Test
    @DisplayName("Link a course to a student")
    @Order(7)
    void linkCourseToStudent(){
        //test fixture
        LinkCourseDTO linkCourseDTO = new LinkCourseDTO();
        linkCourseDTO.setIdCourse(courses.get(0).getId());
        linkCourseDTO.setIdStudent(students.get(3).getUuid());
        LinkCourseDTO linkCourseDTO1 = new LinkCourseDTO();
        linkCourseDTO1.setIdCourse(courses.get(1).getId());
        linkCourseDTO1.setIdStudent(students.get(3).getUuid());

        // create links in db
        CourseLink link = courseLinkAPI.linkCourse(linkCourseDTO);
        CourseLink link1 = courseLinkAPI.linkCourse(linkCourseDTO);
        Assertions.assertNotNull(link);
        Assertions.assertNotNull(link1);

        // get in db
        List<CourseLink> links = courseLinkAPI.getCourseofStudent(students.get(3).getUuid());
        Assertions.assertEquals( 3, links.size()); // there is an existing link in the db
    }
}
