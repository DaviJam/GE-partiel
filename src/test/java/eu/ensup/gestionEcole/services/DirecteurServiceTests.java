package eu.ensup.gestionEcole.services;


import eu.ensup.gestionEcole.dao.DirecteurDao;
import eu.ensup.gestionEcole.domain.Directeur;
import eu.ensup.gestionEcole.service.DirecteurService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
@ExtendWith(MockitoExtension.class)
public class DirecteurServiceTests {

    @InjectMocks
    DirecteurService directeurService;

    @Mock
    DirecteurDao directeurDao;

    @BeforeEach
    void setUp() {

        List<Directeur> arrayListDirecteur = new ArrayList<>();

        arrayListDirecteur.add( new Directeur(null, "directeur1@gmail.com", "password1"));
        arrayListDirecteur.add( new Directeur(null, "directeur2@gmail.com", "password2"));
        arrayListDirecteur.add( new Directeur(null, "directeur3@gmail.com", "password3"));

        when(directeurDao.findAll()).thenReturn(arrayListDirecteur);

        when(directeurDao.findByEmail("directeur1@gmail.com")).thenReturn(arrayListDirecteur.get(0));
    }


    @Test
    public void createDirecteurTest() {

        Directeur directeur = new Directeur(null, "directeur1@gmail.com", "password1");
        when(directeurDao.save(directeur)).thenReturn(directeur);
        directeurService.createDirecteur(directeur);
        verify(directeurDao, times(1)).save(directeur);

    }

    @Test
    void getAllDirecteurTest() {

        List<Directeur> directeurArrayList = directeurService.getAllDirecteur();
        assertEquals(3, directeurArrayList.size() );
        verify(directeurDao, times(1)).findAll();
    }

    @Test
    void getDirecteurByEmailTest() {
        when(directeurDao.findByEmail("directeur1@gmail.com")).thenReturn(new Directeur(null, "directeur1@gmail.com", "password1"));
        Directeur directeur = directeurService.getDirecteurByEmail("directeur1@gmail.com");
        assertEquals("directeur1@gmail.com", directeur.getEmail() );
        assertEquals("password1", directeur.getPassword() );
        verify(directeurDao, times(1)).findByEmail("directeur1@gmail.com");
    }
}
