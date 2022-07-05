package eu.ensup.gestionEcole.controller;

import eu.ensup.gestionEcole.config.PasswordConfig;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.service.EtudiantService;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = EtudiantAPI.class)
@ContextConfiguration(classes = {PasswordConfig.class})
public class EtudiantAPITests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EtudiantService etudiantService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("Should return all students")
    public void getAllStudents() throws Exception {
        List<Etudiant> students = new ArrayList<>();
        students.add( new Etudiant(null, "UUID1","Ali", "Gator","ali.gator@gmail.com", "2 rue des animaux sauvages", "0761615263", LocalDate.now()));
        students.add( new Etudiant(null, "UUID2","Andy", "Capé","andy.cape@gmail.com", "2 rue des fauteuils", "0751624591", LocalDate.now()));
        students.add( new Etudiant(null, "UUID3","titi", "jamii","etudiant3@gmail.com", "adresse3", "telephone3", LocalDate.now()));
        students.add( new Etudiant(null, "UUID4","tutu", "jamiu","etudiant4@gmail.com", "adresse4", "telephone4", LocalDate.now()));
        when(etudiantService.getallStudent()).thenReturn(students);

        this.mockMvc.perform(
                    get("/api/students/getall")
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("UUID1")));

    }

}