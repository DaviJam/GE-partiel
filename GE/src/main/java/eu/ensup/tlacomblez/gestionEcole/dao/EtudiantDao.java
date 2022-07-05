package eu.ensup.tlacomblez.gestionEcole.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.ensup.tlacomblez.gestionEcole.domain.Etudiant;

// Creating a repository for the Etudiant class.
public interface EtudiantDao extends JpaRepository<Etudiant, Long>  {

    
}
