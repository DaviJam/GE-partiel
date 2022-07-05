package eu.ensup.gestionEcole;

import eu.ensup.gestionEcole.config.PasswordConfig;
import eu.ensup.gestionEcole.dao.DirecteurDao;
import eu.ensup.gestionEcole.dao.EcoleDao;
import eu.ensup.gestionEcole.dao.EtudiantDao;
import eu.ensup.gestionEcole.domain.Directeur;
import eu.ensup.gestionEcole.domain.Ecole;
import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	CommandLineRunner start (EcoleDao ecoleDao, DirecteurDao directeurDao, EtudiantDao etudiantDao, PasswordConfig passwordConfig){
		return args -> {
			Directeur directeur = Directeur.builder().id(null).email("directeur@ensup.eu").password(passwordConfig.passwordEncoder().encode("directeur")).build();
			directeurDao.save(directeur);
			Directeur responsable = Directeur.builder().id(null).email("responsable@ensup.eu").password(passwordConfig.passwordEncoder().encode("repsonsable")).build();
			directeurDao.save(responsable);
			ecoleDao.save(Ecole.builder().id(null).nom("Ensup").adresse("Guyancourt").directeur(directeur).telephone("01065241253").email("contact@ensup.eu").build());
			List<Etudiant> students = new ArrayList<>();
			students.add( new Etudiant(null, "UUID1","Ali", "Gator","ali.gator@gmail.com", "2 rue des animaux sauvages", "0761615263", LocalDate.now()));
			students.add( new Etudiant(null, "UUID2","Andy", "Capé","andy.cape@gmail.com", "2 rue des fauteuils", "0751624591", LocalDate.now()));
			students.add( new Etudiant(null, "UUID3","titi", "jamii","etudiant3@gmail.com", "adresse3", "telephone3", LocalDate.now()));
			students.add( new Etudiant(null, "UUID4","tutu", "jamiu","etudiant4@gmail.com", "adresse4", "telephone4", LocalDate.now()));
			students.forEach(etudiant -> etudiantDao.save(etudiant));
		};
	}
}
