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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SuiteDisplayName("Unit test")
@ContextConfiguration(classes = {PasswordConfig.class, CourseLinkAPI.class, CourseLinkService.class})
@WebMvcTest(value = CourseLinkAPI.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CourseLinkAPITests {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private CourseLinkService courseLinkService;

    @BeforeEach
    void setup(){

    }

    @Test
    @DisplayName("Link course and student")
    public void associate() throws Exception{
        CourseLink courseLink = new CourseLink(null, "0", 0L);
        when(courseLinkService.associate(any(String.class),any(Long.class))).thenReturn(courseLink);
        MvcResult res = mockMvc.perform(
                get("/api/link/0&0")
        ).andDo(print()).andExpect(status().isOk()).andReturn();

        //verify
        verify(courseLinkService).associate(any(String.class),any(Long.class));
        // check content
        Assertions.assertTrue(res.getResponse().getContentAsString().contains("0"));
    }
}
