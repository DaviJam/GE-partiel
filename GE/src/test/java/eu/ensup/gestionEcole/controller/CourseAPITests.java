package eu.ensup.gestionEcole.controller;

import eu.ensup.gestionEcole.config.PasswordConfig;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.service.CourseService;
import eu.ensup.gestionEcole.service.EtudiantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@ContextConfiguration(classes = {PasswordConfig.class, CourseAPI.class, CourseService.class})
@WebMvcTest(value = CourseAPI.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CourseAPITests {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private List<Cours> courseList;

    @BeforeEach
    void setup(){
        courseList = new ArrayList<>();
        courseList.add(new Cours(null, "Math", 50));
        courseList.add(new Cours(null, "Francais", 10));
        courseList.add(new Cours(null, "Histoire", 43));
    }

    @Test
    @DisplayName("Should return all courses")
    public void getAllCourse() throws Exception {
        when(courseService.getallCourse()).thenReturn(courseList);
        MvcResult res = mockMvc.perform(
                get("/api/course")
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        //verify
        verify(courseService).getallCourse();
        // check content
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("Math"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("Francais"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("Histoire"));
    }
}
