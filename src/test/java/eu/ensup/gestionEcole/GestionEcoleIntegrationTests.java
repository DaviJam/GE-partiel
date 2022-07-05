package eu.ensup.gestionEcole;

import eu.ensup.gestionEcole.integration.IntegrationTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Suite
@SelectClasses({
        IntegrationTests.class
})
public class GestionEcoleIntegrationTests {
}
