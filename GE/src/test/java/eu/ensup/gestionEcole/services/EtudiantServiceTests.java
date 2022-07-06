package eu.ensup.gestionEcole.services;

import eu.ensup.gestionEcole.dao.EtudiantDao;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.service.EtudiantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTests {

    @InjectMocks
    EtudiantService etudiantService;

    @Mock
    EtudiantDao etudiantDao;

    List<Etudiant> students;


    @BeforeEach
    void setUp() {
    }


    @Test
    public void createEtudiantTest() {
        Etudiant etudiant = new Etudiant(null,"UUID1","étudiant", "4", "etudiant4@gmail.com", "adresse4", "telephone4", LocalDate.now());

        when(etudiantDao.save(etudiant)).thenReturn(etudiant);
        etudiantService.createStudent(etudiant);
        verify(etudiantDao, times(1)).save(etudiant);

    }

    @Test
    void getAllEtudiantTests() {
        students = new ArrayList<>();

        students.add( new Etudiant(null, "UUID1","toto", "jamio","etudiant1@gmail.com", "adresse1", "telephone1", LocalDate.now()));
        students.add( new Etudiant(null, "UUID2","tata", "jamia","etudiant2@gmail.com", "adresse1", "telephone1", LocalDate.now()));
        students.add( new Etudiant(null, "UUID3","titi", "jamii","etudiant3@gmail.com", "adresse1", "telephone1", LocalDate.now()));
        students.add( new Etudiant(null, "UUID4","tutu", "jamiu","etudiant4@gmail.com", "adresse1", "telephone1", LocalDate.now()));

        when(etudiantDao.findAll()).thenReturn(students);

        List<Etudiant> etudiantArrayList = etudiantService.getallStudent();
        assertEquals(4, etudiantArrayList.size() );
        verify(etudiantDao, times(1)).findAll();
    }

    @Test
    @DisplayName("Update student")
    void updateStudent(){
        Etudiant updatedStudent = new Etudiant(null,"UUID1","étudiant", "4", "etudiant4@gmail.com", "new orleans", "telephone4", LocalDate.now());
        when(etudiantDao.save(any(Etudiant.class))).thenReturn(updatedStudent);
        when(etudiantDao.findByUuid(any(String.class))).thenReturn(updatedStudent);
        Etudiant upStudent = etudiantService.updateStudent(updatedStudent);
        verify(etudiantDao, times(1)).save(any(Etudiant.class));
        verify(etudiantDao, times(1)).findByUuid(any(String.class));
        Assertions.assertEquals(upStudent.getUuid(), updatedStudent.getUuid());
        Assertions.assertEquals(upStudent.getAdresse(), updatedStudent.getAdresse());
        Assertions.assertEquals(upStudent.getEmail(), updatedStudent.getEmail());
        Assertions.assertEquals(upStudent.getDateNaissance(), updatedStudent.getDateNaissance());
        Assertions.assertEquals(upStudent.getTelephone(), updatedStudent.getTelephone());
        Assertions.assertEquals(upStudent.getNom(), updatedStudent.getNom());
        Assertions.assertEquals(upStudent.getPrenom(), updatedStudent.getPrenom());
    }

    @Test
    @DisplayName("get by one")
    void getStudent(){
        Etudiant updatedStudent = new Etudiant(null,"UUID1","étudiant", "4", "etudiant4@gmail.com", "new orleans", "telephone4", LocalDate.now());
        when(etudiantDao.findByUuid(any(String.class))).thenReturn(updatedStudent);
        Etudiant student = etudiantService.getStudent("UUIDMachin");

        verify(etudiantDao, times(1)).findByUuid(any(String.class));
        Assertions.assertEquals(student.getUuid(), updatedStudent.getUuid());
        Assertions.assertEquals(student.getAdresse(), updatedStudent.getAdresse());
        Assertions.assertEquals(student.getEmail(), updatedStudent.getEmail());
        Assertions.assertEquals(student.getDateNaissance(), updatedStudent.getDateNaissance());
        Assertions.assertEquals(student.getTelephone(), updatedStudent.getTelephone());
        Assertions.assertEquals(student.getNom(), updatedStudent.getNom());
        Assertions.assertEquals(student.getPrenom(), updatedStudent.getPrenom());
    }

    @Test
    @DisplayName("Delete student")
    void deleteStudent(){
        Mockito.doNothing().when(etudiantDao).deleteEtudiantByUuid(any(String.class));
        etudiantService.deleteStudent("UUID1");
        verify(etudiantDao).deleteEtudiantByUuid(any(String.class));
    }

}
