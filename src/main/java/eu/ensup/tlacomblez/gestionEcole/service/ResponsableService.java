package eu.ensup.tlacomblez.gestionEcole.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.ensup.tlacomblez.gestionEcole.dao.ResponsableDao;
import eu.ensup.tlacomblez.gestionEcole.domain.Responsable;

@Service
public class ResponsableService {

    @Autowired
    ResponsableDao responsableDao;


    public Responsable creationResponsable(Responsable responsable) {
        return responsableDao.save(responsable);
    }

    public List<Responsable> getAllResponsable() {
        return responsableDao.findAll();
    }

    public Responsable getResponsableByEmail(String email) {
        return responsableDao.findByEmail(email);
    }

}
