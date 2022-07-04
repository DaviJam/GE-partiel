package eu.ensup.gestionEcole.dao;

import eu.ensup.gestionEcole.domain.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcoleDao extends JpaRepository<Ecole, Long> {
}
