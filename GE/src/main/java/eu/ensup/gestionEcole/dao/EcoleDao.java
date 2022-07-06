package eu.ensup.gestionEcole.dao;

import eu.ensup.gestionEcole.domain.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Ecole dao.
 */
public interface EcoleDao extends JpaRepository<Ecole, Long> {
}
