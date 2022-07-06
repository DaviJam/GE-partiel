package eu.ensup.gestionEcole.service;

import java.util.List;
import java.util.UUID;

import eu.ensup.gestionEcole.dao.EtudiantDao;
import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * The type Etudiant service.
 */
@Service
@Transactional
public class EtudiantService {

    /**
     * The Etudiant repository.
     */
    @Autowired
    EtudiantDao etudiantRepository;

    /**
     * It creates a new student in the database
     *
     * @param etudiant the object to be created
     * @return Etudiant etudiant
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
     * @param uuid the uuid
     * @return A student in the database.
     */
    public Etudiant getStudent(String uuid){ return etudiantRepository.findByUuid(uuid); }

    /**
     * Update student etudiant.
     *
     * @param etudiant the etudiant
     * @return the etudiant
     */
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

    /**
     * Delete student.
     *
     * @param uuid the uuid
     */
    public void deleteStudent(String uuid){
        etudiantRepository.deleteEtudiantByUuid(uuid);}
}
