package eu.ensup.tlacomblez.gestionEcole.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.ensup.tlacomblez.gestionEcole.dao.EtudiantDao;
import eu.ensup.tlacomblez.gestionEcole.domain.Etudiant;

@Service
public class EtudiantService {
    
    @Autowired
    EtudiantDao etudiantRepository;

    /**
     * It creates a new student in the database
     * 
     * @param etudiant the object to be created
     * @return Etudiant
     */
    public Etudiant creerEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    /**
     * It returns a list of all the students in the database
     * 
     * @return A list of all the students in the database.
     */
    public List<Etudiant> getAllEtudiant() {
        return etudiantRepository.findAll();
    }
}
