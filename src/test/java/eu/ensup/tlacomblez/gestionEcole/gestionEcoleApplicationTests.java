package eu.ensup.tlacomblez.gestionEcole;

import eu.ensup.tlacomblez.gestionEcole.services.DirecteurServiceTests;
import eu.ensup.tlacomblez.gestionEcole.services.EtudiantServiceTests;
import eu.ensup.tlacomblez.gestionEcole.services.ResponsableServiceTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Suite
@SelectClasses({
        DirecteurServiceTests.class,
        EtudiantServiceTests.class,
        ResponsableServiceTests.class
})
class gestionEcoleApplicationTests {
}
