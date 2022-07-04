package eu.ensup.gestionEcole.services;

import eu.ensup.gestionEcole.dao.EtudiantDao;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.service.EtudiantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class EtudiantServiceTests {

    @Autowired
    EtudiantService etudiantService;

    @MockBean
    EtudiantDao etudiantDao;

    List<Etudiant> students;


    @BeforeEach
    void setUp() {

        students = new ArrayList<>();

        students.add( new Etudiant(null, "UUID1","toto", "jamio","etudiant1@gmail.com", "adresse1", "telephone1", LocalDate.now()));
        students.add( new Etudiant(null, "UUID2","tata", "jamia","etudiant2@gmail.com", "adresse1", "telephone1", LocalDate.now()));
        students.add( new Etudiant(null, "UUID3","titi", "jamii","etudiant3@gmail.com", "adresse1", "telephone1", LocalDate.now()));
        students.add( new Etudiant(null, "UUID4","tutu", "jamiu","etudiant4@gmail.com", "adresse1", "telephone1", LocalDate.now()));


        when(etudiantDao.findAll()).thenReturn(students);
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

        List<Etudiant> etudiantArrayList = etudiantService.getallStudent();
        assertEquals(3, etudiantArrayList.size() );
        verify(etudiantDao, times(1)).findAll();
    }
}
