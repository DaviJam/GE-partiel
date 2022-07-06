package eu.ensup.gestionEcole;

import eu.ensup.gestionEcole.config.PasswordConfig;
import eu.ensup.gestionEcole.dao.*;
import eu.ensup.gestionEcole.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * The type Gestion ecole application.
 */
@SpringBootApplication
@EnableDiscoveryClient

/**
 * This class is the entry point of the application.
 */
public class gestionEcoleApplication {


	/**
	 * The main() method uses Spring Boot’s SpringApplication.run() method to launch an application
	 *
	 * @param args list of args to passed to the app
	 */
	public static void main(String[] args) {
		SpringApplication.run(gestionEcoleApplication.class, args);
	}


	/**
	 * Start command line runner.
	 *
	 * @param ecoleDao       the ecole dao
	 * @param directeurDao   the directeur dao
	 * @param etudiantDao    the etudiant dao
	 * @param courseDao      the course dao
	 * @param courseLinkDao  the course link dao
	 * @param passwordConfig the password config
	 * @return the command line runner
	 */
	@Bean
	CommandLineRunner start (
			EcoleDao ecoleDao,
			DirecteurDao directeurDao,
			EtudiantDao etudiantDao,
			CourseDao courseDao,
			CourseLinkDao courseLinkDao,
			PasswordConfig passwordConfig){
		return args -> {
			Directeur directeur = Directeur.builder().id(null).email("directeur@ensup.eu").password(passwordConfig.passwordEncoder().encode("directeur")).build();
			directeurDao.save(directeur);
			Directeur responsable = Directeur.builder().id(null).email("responsable@ensup.eu").password(passwordConfig.passwordEncoder().encode("repsonsable")).build();
			directeurDao.save(responsable);
			ecoleDao.save(Ecole.builder().id(null).nom("Ensup").adresse("Guyancourt").directeur(directeur).telephone("01065241253").email("contact@ensup.eu").build());
			List<Etudiant> students = new ArrayList<>();
			students.add(0, new Etudiant(null, "UUID1","Ali", "Gator","ali.gator@gmail.com", "2 rue des animaux sauvages", "0761615263", LocalDate.now()));
			students.add(1,  new Etudiant(null, "UUID2","Andy", "Capé","andy.cape@gmail.com", "2 rue des fauteuils", "0751624591", LocalDate.now()));
			students.add(2, new Etudiant(null, "UUID3","titi", "jamii","etudiant3@gmail.com", "adresse3", "telephone3", LocalDate.now()));
			students.add(3, new Etudiant(null, "UUID4","tutu", "jamiu","etudiant4@gmail.com", "adresse4", "telephone4", LocalDate.now()));
			students.forEach(etudiant -> etudiantDao.save(etudiant));

			List<Cours> coursList = new ArrayList<>();
			coursList.add(Cours.builder().id(null).theme("Math").nbHeures(15).build());
			coursList.add(Cours.builder().id(null).theme("Java").nbHeures(15).build());
			coursList.add(Cours.builder().id(null).theme("Cloud").nbHeures(15).build());
			coursList.add(Cours.builder().id(null).theme("IT").nbHeures(15).build());
			coursList.add(Cours.builder().id(null).theme("Angular").nbHeures(15).build());
			coursList.forEach(cours -> courseDao.save(cours));

			List<CourseLink> courseLinklist = new ArrayList<>();
			courseLinklist.add(CourseLink.builder().idStudent("UUID1").idCourse(0L).build());
			courseLinklist.add(CourseLink.builder().idStudent("UUID2").idCourse(1L).build());
			courseLinklist.add(CourseLink.builder().idStudent("UUID3").idCourse(0L).build());
			courseLinklist.add(CourseLink.builder().idStudent("UUID4").idCourse(1L).build());
			courseLinklist.add(CourseLink.builder().idStudent("UUID1").idCourse(1L).build());
			courseLinklist.forEach(courseLink -> courseLinkDao.save(courseLink));
		};
	}
}
