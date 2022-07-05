package eu.ensup.gestionEcole.controller;

import eu.ensup.gestionEcole.config.PasswordConfig;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.CourseLink;
import eu.ensup.gestionEcole.service.CourseLinkService;
import eu.ensup.gestionEcole.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ContextConfiguration(classes = {PasswordConfig.class, CourseLinkAPI.class, CourseLinkService.class})
@WebMvcTest(value = CourseLinkAPI.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CourseLinkAPITests {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private CourseLinkService courseLinkService;

    List<CourseLink> courseLinklist;
    @BeforeEach
    void setup(){
        courseLinklist = new ArrayList<>();
        courseLinklist.add(CourseLink.builder().idStudent("UUID1").idCourse(0L).build());
        courseLinklist.add(CourseLink.builder().idStudent("UUID1").idCourse(2L).build());
        courseLinklist.add(CourseLink.builder().idStudent("UUID2").idCourse(1L).build());
        courseLinklist.add(CourseLink.builder().idStudent("UUID3").idCourse(0L).build());
        courseLinklist.add(CourseLink.builder().idStudent("UUID4").idCourse(1L).build());
    }

    @Test
    @DisplayName("Link course and student")
    public void associate() throws Exception{
        CourseLink courseLink = new CourseLink(null, "UUID1", 0L);
        when(courseLinkService.associate(any(String.class),any(Long.class))).thenReturn(courseLink);
        MvcResult res = mockMvc.perform(
                post("/api/link/").content(
                        "{\"idStudent\": \"UUID1\"," +
                        "\"idCourse\": \"0\""+
                        "}").contentType(MediaType.APPLICATION_JSON)

        ).andDo(print()).andExpect(status().isOk()).andReturn();

        //verify
        verify(courseLinkService).associate(any(String.class),any(Long.class));
        // check content
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID1"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("0"));
    }

    @Test
    @DisplayName("getCourseofStudent")
    public void getCourseofStudent() throws Exception{
        List<CourseLink> listCourse = courseLinklist.stream().filter(courseLink -> courseLink.getIdStudent().compareTo("UUID1")==0).collect(Collectors.toList());
        when(courseLinkService.getCourseofStudent(any(String.class))).thenReturn(listCourse);

        MvcResult res = mockMvc.perform(
                get("/api/link/UUID1")
        ).andDo(print()).andExpect(status().isOk()).andReturn();

        // verify
        verify(courseLinkService).getCourseofStudent(any(String.class));

        // check content
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID1"));
    }

    @Test
    @DisplayName("getAllCourseLink")
    public void getAllCourseLink() throws Exception{

        when(courseLinkService.getAllLink()).thenReturn(courseLinklist);
        MvcResult res = mockMvc.perform(
                get("/api/link/getall")
        ).andDo(print()).andExpect(status().isOk()).andReturn();

        // verify
        verify(courseLinkService).getAllLink();

        // check content
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID1"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID2"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID3"));
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("UUID4"));
    }

}
