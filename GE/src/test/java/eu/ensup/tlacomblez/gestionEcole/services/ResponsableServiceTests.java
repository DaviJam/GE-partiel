package eu.ensup.tlacomblez.gestionEcole.services;

import eu.ensup.tlacomblez.gestionEcole.dao.ResponsableDao;
import eu.ensup.tlacomblez.gestionEcole.dao.ResponsableDao;
import eu.ensup.tlacomblez.gestionEcole.domain.Responsable;
import eu.ensup.tlacomblez.gestionEcole.domain.Responsable;
import eu.ensup.tlacomblez.gestionEcole.service.ResponsableService;
import eu.ensup.tlacomblez.gestionEcole.service.ResponsableService;
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
import static org.mockito.Mockito.times;

@ActiveProfiles("test")
@SpringBootTest
public class ResponsableServiceTests {

    @Autowired
    ResponsableService responsableService;

    @MockBean
    ResponsableDao responsableDao;

    @BeforeEach
    void setUp() {

        List<Responsable> arrayListResponsable = new ArrayList<>();

        arrayListResponsable.add( new Responsable("responsable", "1", "Responsable1@gmail.com", "password1", "adresse1", "telephone1", LocalDate.now()) );
        arrayListResponsable.add( new Responsable("responsable", "2", "Responsable2@gmail.com", "password2", "adresse2", "telephone2", LocalDate.now()) );
        arrayListResponsable.add( new Responsable("responsable", "3", "Responsable3@gmail.com", "password3", "adresse3", "telephone3", LocalDate.now()) );

        when(responsableDao.findAll()).thenReturn(arrayListResponsable);

        when(responsableDao.findByEmail("Responsable1@gmail.com")).thenReturn(arrayListResponsable.get(0));
    }


    @Test
    public void createResponsableTest() {

        Responsable Responsable = new Responsable("responsable", "4", "Responsable4@gmail.com", "password4", "adresse4", "telephone4", LocalDate.now());

        when(responsableDao.save(Responsable)).thenReturn(Responsable);

        responsableService.creationResponsable(Responsable);


        verify(responsableDao, times(1)).save(Responsable);

    }

    @Test
    void getAllResponsableTest() {

        List<Responsable> ResponsableArrayList = responsableService.getAllResponsable();
        assertEquals(3, ResponsableArrayList.size() );
        verify(responsableDao, times(1)).findAll();
    }

    @Test
    void getResponsableByEmailTest() {
        when(responsableDao.findByEmail("Responsable1@gmail.com")).thenReturn(new Responsable("responsable", "1", "Responsable1@gmail.com", "password1", "adresse1", "telephone1", LocalDate.now()));
        Responsable Responsable = responsableService.getResponsableByEmail("Responsable1@gmail.com");
        assertEquals("Responsable1@gmail.com", Responsable.getEmail() );
        assertEquals("responsable", Responsable.getNom() );
        assertEquals("1", Responsable.getPrenom() );
        verify(responsableDao, times(1)).findByEmail("Responsable1@gmail.com");
    }

}
