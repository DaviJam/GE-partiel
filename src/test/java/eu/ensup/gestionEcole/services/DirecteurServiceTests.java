//package eu.ensup.gestionEcole.services;
//
//
//import eu.ensup.gestionEcole.dao.DirecteurDao;
//import eu.ensup.gestionEcole.domain.Directeur;
//import eu.ensup.gestionEcole.service.DirecteurService;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ActiveProfiles("test")
//@SpringBootTest
//public class DirecteurServiceTests {
//
//    @Autowired
//    DirecteurService directeurService;
//
//    @MockBean
//    DirecteurDao directeurDao;
//
//    @BeforeEach
//    void setUp() {
//
//        List<Directeur> arrayListDirecteur = new ArrayList<>();
//
//        arrayListDirecteur.add( new Directeur("directeur", "1", "directeur1@gmail.com", "password1", "adresse1", "telephone1", LocalDate.now()) );
//        arrayListDirecteur.add( new Directeur("directeur", "2", "directeur2@gmail.com", "password2", "adresse2", "telephone2", LocalDate.now()) );
//        arrayListDirecteur.add( new Directeur("directeur", "3", "directeur3@gmail.com", "password3", "adresse3", "telephone3", LocalDate.now()) );
//
//        when(directeurDao.findAll()).thenReturn(arrayListDirecteur);
//
//        when(directeurDao.findByEmail("directeur1@gmail.com")).thenReturn(arrayListDirecteur.get(0));
//    }
//
//
//    @Test
//    public void createDirecteurTest() {
//
//        Directeur directeur = new Directeur("directeur", "4", "directeur4@gmail.com", "password4", "adresse4", "telephone4", LocalDate.now());
//        when(directeurDao.save(directeur)).thenReturn(directeur);
//        directeurService.createDirecteur(directeur);
//        verify(directeurDao, times(1)).save(directeur);
//
//    }
//
//    @Test
//    void getAllDirecteurTest() {
//
//        List<Directeur> directeurArrayList = directeurService.getAllDirecteur();
//        assertEquals(3, directeurArrayList.size() );
//        verify(directeurDao, times(1)).findAll();
//    }
//
//    @Test
//    void getDirecteurByEmailTest() {
//        when(directeurDao.findByEmail("directeur1@gmail.com")).thenReturn(new Directeur("directeur", "1", "directeur1@gmail.com", "password1", "adresse1", "telephone1", LocalDate.now()));
//        Directeur directeur = directeurService.getDirecteurByEmail("directeur1@gmail.com");
//        assertEquals("directeur1@gmail.com", directeur.getEmail() );
//        assertEquals("directeur", directeur.getNom() );
//        assertEquals("1", directeur.getPrenom() );
//        verify(directeurDao, times(1)).findByEmail("directeur1@gmail.com");
//    }
//}
