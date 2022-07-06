package eu.ensup.gestionEcole.dao;

import eu.ensup.gestionEcole.domain.Directeur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Directeur dao.
 */
// Extending the JpaRepository interface.
public interface DirecteurDao extends JpaRepository<Directeur, Long>  {
    /**
     * Find by email directeur.
     *
     * @param email the email
     * @return the directeur
     */
    public Directeur findByEmail( String email);
}