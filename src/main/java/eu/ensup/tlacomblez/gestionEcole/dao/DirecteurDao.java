package eu.ensup.tlacomblez.gestionEcole.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eu.ensup.tlacomblez.gestionEcole.domain.Directeur;

// Extending the JpaRepository interface.
public interface DirecteurDao extends JpaRepository<Directeur, Long>  {

    @Query(value = "SELECT * FROM personne p WHERE p.email = ?1 and p.dtype = 'Directeur'", nativeQuery = true)
    // A method that will be used to find a directeur by its email.
    public Directeur findByEmail( String email);
}