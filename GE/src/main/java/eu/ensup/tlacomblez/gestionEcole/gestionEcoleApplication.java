package eu.ensup.tlacomblez.gestionEcole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
/**
 * This class is the entry point of the application.
 */
public class gestionEcoleApplication {


	/**
	 * The main() method uses Spring Boot’s SpringApplication.run() method to launch an application
	 * @param args list of args to passed to the app
	 */
	public static void main(String[] args) {
		SpringApplication.run(gestionEcoleApplication.class, args);
	}

}
