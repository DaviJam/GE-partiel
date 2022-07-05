package eu.ensup.gestionEcole.dao;

import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

// Creating a repository for the Etudiant class.
public interface EtudiantDao extends JpaRepository<Etudiant, Long>  {
    public Etudiant findByUuid(String uuid);
    public void deleteEtudiantByUuid(String uuid);
}
