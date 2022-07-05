package eu.ensup.tlacomblez.gestionEcole.services;

import eu.ensup.tlacomblez.gestionEcole.dao.DirecteurDao;
import eu.ensup.tlacomblez.gestionEcole.dao.EtudiantDao;
import eu.ensup.tlacomblez.gestionEcole.domain.Directeur;
import eu.ensup.tlacomblez.gestionEcole.domain.Etudiant;
import eu.ensup.tlacomblez.gestionEcole.service.DirecteurService;
import eu.ensup.tlacomblez.gestionEcole.service.EtudiantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class EtudiantServiceTests {

    @Autowired
    EtudiantService etudiantService;

    @MockBean
    EtudiantDao etudiantDao;

    @BeforeEach
    void setUp() {

        List<Etudiant> arrayListEtudiant = new ArrayList<>();

        arrayListEtudiant.add( new Etudiant("étudiant", "1", "etudiant1@gmail.com", "adresse1", "telephone1", LocalDate.now()) );
        arrayListEtudiant.add( new Etudiant("étudiant", "2", "etudiant2@gmail.com", "adresse2", "telephone2", LocalDate.now()) );
        arrayListEtudiant.add( new Etudiant("étudiant", "3", "etudiant3@gmail.com", "adresse3", "telephone3", LocalDate.now()) );
        when(etudiantDao.findAll()).thenReturn(arrayListEtudiant);

    }

    @Test
    public void createEtudiantTest() {
        Etudiant etudiant = new Etudiant("étudiant", "4", "etudiant4@gmail.com", "adresse4", "telephone4", LocalDate.now());

        when(etudiantDao.save(etudiant)).thenReturn(etudiant);
        etudiantService.creerEtudiant(etudiant);
        verify(etudiantDao, times(1)).save(etudiant);

    }

    @Test
    void getAllEtudiantTests() {

        List<Etudiant> etudiantArrayList = etudiantService.getAllEtudiant();
        assertEquals(3, etudiantArrayList.size() );
        verify(etudiantDao, times(1)).findAll();
    }
}
