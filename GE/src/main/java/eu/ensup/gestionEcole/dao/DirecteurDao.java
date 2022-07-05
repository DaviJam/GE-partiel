package eu.ensup.gestionEcole.dao;

import eu.ensup.gestionEcole.domain.Directeur;
import org.springframework.data.jpa.repository.JpaRepository;

// Extending the JpaRepository interface.
public interface DirecteurDao extends JpaRepository<Directeur, Long>  {
    public Directeur findByEmail( String email);
}