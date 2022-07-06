package eu.ensup.gestionEcole;

import eu.ensup.gestionEcole.controller.CourseAPITests;
import eu.ensup.gestionEcole.controller.CourseLinkAPITests;
import eu.ensup.gestionEcole.controller.EtudiantAPITests;
import eu.ensup.gestionEcole.services.CourseDaoTests;
import eu.ensup.gestionEcole.services.CourseLinkServiceTests;
import eu.ensup.gestionEcole.services.DirecteurServiceTests;
import eu.ensup.gestionEcole.services.EtudiantServiceTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * The type Gestion ecole application tests.
 */
@SpringBootTest
@ActiveProfiles("test")
@Suite
@SelectClasses({
        DirecteurServiceTests.class,
        EtudiantServiceTests.class,
        CourseLinkServiceTests.class,
        CourseDaoTests.class,
        CourseAPITests.class,
        CourseLinkAPITests.class,
        EtudiantAPITests.class
})
class gestionEcoleApplicationTests {
}
