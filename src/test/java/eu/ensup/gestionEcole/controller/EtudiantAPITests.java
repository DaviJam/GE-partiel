package eu.ensup.gestionEcole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.ensup.gestionEcole.config.PasswordConfig;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.service.EtudiantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

@ActiveProfiles("test")
@ContextConfiguration(classes = {PasswordConfig.class, EtudiantAPI.class, EtudiantService.class})
@WebMvcTest(value = EtudiantAPI.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class EtudiantAPITests {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private EtudiantService etudiantService;

    private List<Etudiant> students;

    @BeforeEach
    void setup(){
        students = new ArrayList<>();
        students.add( new Etudiant(null, "UUID1","Ali", "Gator","ali.gator@gmail.com", "2 rue des animaux sauvages", "0761615263", LocalDate.now()));
        students.add( new Etudiant(null, "UUID2","Andy", "Capé","andy.cape@gmail.com", "2 rue des fauteuils", "0751624591", LocalDate.now()));
        students.add( new Etudiant(null, "UUID3","titi", "jamii","etudiant3@gmail.com", "adresse3", "telephone3", LocalDate.now()));
        students.add( new Etudiant(null, "UUID4","tutu", "jamiu","etudiant4@gmail.com", "adresse4", "telephone4", LocalDate.now()));
    }

    @Test
    @DisplayName("Should return all students")
    public void getAllStudents() throws Exception {
        when(etudiantService.getallStudent()).thenReturn(students);
        MvcResult res = mockMvc.perform(
                get("/api/students/getall")
                        ).andDo(print()).andExpect(status().isOk()).andReturn();
        //verify
        verify(etudiantService).getallStudent();
        // check content
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID1"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID2"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID3"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID4"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("Ali"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("Andy"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("titi"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("tutu"));
    }

    @Test
    @DisplayName("Should return a student")
    public void getOneStudent() throws Exception {
        when(etudiantService.getStudent(any(String.class))).thenReturn(students.get(0));
        MvcResult res = mockMvc.perform(
                get("/api/students/get/0")
        ).andDo(print()).andExpect(status().isOk()).andReturn();

        // verify
        verify(etudiantService).getStudent(any(String.class));

        // check content
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID1"));
    }

    @Test
    @DisplayName("Should add a student")
    public void addStudent() throws Exception {
        when(etudiantService.createStudent(any(Etudiant.class))).thenReturn(students.get(0));
        MvcResult res = mockMvc.perform(
                post("/api/students/add").content(
                        "{\"id\": \"null\"," +
                        "\"uuid\": \"0\"," +
                        "\"nom\": \"totdadadao\"," +
                        "\"prenom\": \"momodifié\"," +
                        "\"email\": \"toto.momo@email.com\"," +
                        "\"adresse\": \"15 rue de champ, boischaux\"," +
                        "\"telephone\": \"0514526325\"," +
                        "\"dateNaissance\": \"2000-06-29\"" +
                        " }").contentType(MediaType.APPLICATION_JSON)
                ).andDo(print()).andReturn();
        verify(etudiantService).createStudent(any(Etudiant.class));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID1"));
    }

    @Test
    @DisplayName("Should update a student")
    public void updateStudent() throws Exception {
        Etudiant student = new Etudiant(null, "UUID5","nommodif", "letgo","jo.letgo@gmail.com", "2 rue des animaux gentils", "0761615263", LocalDate.now());
        when(etudiantService.updateStudent(any(Etudiant.class))).thenReturn(students.get(0));
        MvcResult res = mockMvc.perform(
                post("/api/students/update").content(
                        "{\"id\":\"null\"," +
                                "\"uuid\":\"0\"," +
                                "\"nom\":\"totdadadao\"," +
                                "\"prenom\":\"momodifié\"," +
                                "\"email\":\"toto.momo@email.com\"," +
                                "\"adresse\":\"15 rue de champ, boischaux\"," +
                                "\"telephone\":\"0514526325\"," +
                                "\"dateNaissance\":\"2000-06-29\"" +
                                " }").contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        verify(etudiantService).updateStudent(any(Etudiant.class));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("Ali"));
    }

    @Test
    @DisplayName("Should delete a student")
    public void deleteStudent() throws Exception {
        when(etudiantService.getStudent(any(String.class))).thenReturn(null);

        Mockito.doNothing().when(etudiantService).deleteStudent(any(String.class));
        MvcResult res = mockMvc.perform(
                get("/api/students/delete/0")
        ).andDo(print()).andExpect(status().isOk()).andReturn();

        verify(etudiantService).getStudent(any(String.class));
        verify(etudiantService).deleteStudent(any(String.class));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("L'étudiant a bien été supprimé"));
    }

}