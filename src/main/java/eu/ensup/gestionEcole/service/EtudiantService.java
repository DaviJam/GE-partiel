package eu.ensup.gestionEcole.service;

import java.util.List;
import java.util.UUID;

import eu.ensup.gestionEcole.dao.EtudiantDao;
import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Etudiant createStudent(Etudiant etudiant) {
        etudiant.setUuid(String.valueOf(UUID.randomUUID()));
        return etudiantRepository.save(etudiant);
    }

    /**
     * It returns a list of all the students in the database
     * 
     * @return A list of all the students in the database.
     */
    public List<Etudiant> getallStudent() {
        return etudiantRepository.findAll();
    }

    /**
     * It returns a student in the database
     *
     * @return A student in the database.
     */
    public Etudiant getStudent(String uuid){ return etudiantRepository.findByUuid(uuid); }

    public Etudiant updateStudent(Etudiant etudiant){
        Etudiant student = etudiantRepository.findByUuid(etudiant.getUuid());
        student.setNom(etudiant.getNom());
        student.setPrenom(etudiant.getPrenom());
        student.setAdresse(etudiant.getAdresse());
        student.setDateNaissance(etudiant.getDateNaissance());
        student.setTelephone(etudiant.getTelephone());
        student.setEmail(etudiant.getEmail());
        return etudiantRepository.save(student);
    }

    public void deleteStudent(Etudiant etudiant){ etudiantRepository.delete(etudiant);}
}
