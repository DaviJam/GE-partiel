package eu.ensup.gestionEcole.dao;

import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Etudiant dao.
 */
// Creating a repository for the Etudiant class.
public interface EtudiantDao extends JpaRepository<Etudiant, Long>  {
    /**
     * Find by uuid etudiant.
     *
     * @param uuid the uuid
     * @return the etudiant
     */
    public Etudiant findByUuid(String uuid);

    /**
     * Delete etudiant by uuid.
     *
     * @param uuid the uuid
     */
    public void deleteEtudiantByUuid(String uuid);
}
