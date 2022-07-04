package eu.ensup.gestionEcole;

import eu.ensup.gestionEcole.config.PasswordConfig;
import eu.ensup.gestionEcole.dao.DirecteurDao;
import eu.ensup.gestionEcole.dao.EcoleDao;
import eu.ensup.gestionEcole.domain.Directeur;
import eu.ensup.gestionEcole.domain.Ecole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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


	@Bean
	CommandLineRunner start (EcoleDao ecoleDao, DirecteurDao directeurDao, PasswordConfig passwordConfig){
		return args -> {
			Directeur directeur = Directeur.builder().id(null).email("directeur@ensup.eu").password(passwordConfig.passwordEncoder().encode("directeur")).build();
			directeurDao.save(directeur);
			ecoleDao.save(Ecole.builder().id(null).nom("Ensup").adresse("Guyancourt").directeur(directeur).telephone("01065241253").email("contact@ensup.eu").build());
		};
	}
}
