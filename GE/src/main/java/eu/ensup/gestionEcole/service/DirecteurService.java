package eu.ensup.gestionEcole.service;

import java.util.List;

import eu.ensup.gestionEcole.dao.DirecteurDao;
import eu.ensup.gestionEcole.domain.Directeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * The type Directeur service.
 */
@Service
@Transactional
/**
 * A class that is used to manage the services of a director.
 */
public class DirecteurService {

    /**
     * The Directeur dao.
     */
// Injecting the directeurDao object into the directeurService object.
    @Autowired
    DirecteurDao directeurDao;

    /**
     * It creates a new Directeur object and saves it to the database.
     *
     * @param directeur The Directeur object to be created.
     * @return The directeur object is being returned.
     */
    public Directeur createDirecteur(Directeur directeur) {
        return directeurDao.save(directeur);
    }

    /**
     * This function returns a list of all the directors in the database
     *
     * @return A list of all the directeurs in the database.
     */
    public List<Directeur> getAllDirecteur() {
        return directeurDao.findAll();
    }

    /**
     * Get the director by email.
     *
     * @param email the email of the directeur
     * @return A directeur object
     */
    public Directeur getDirecteurByEmail(String email) {
        return directeurDao.findByEmail(email);
    }
}
