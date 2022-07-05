package eu.ensup.gestionEcole.service;

import eu.ensup.gestionEcole.dao.CourseLinkDao;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.CourseLink;
import eu.ensup.gestionEcole.dao.EtudiantDao;
import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseLinkService {

    @Autowired
    CourseLinkDao courseLinkDao;

    public CourseLink associate(String etudiantUuId, Long courseId){
        CourseLink courseLink = new CourseLink(null, etudiantUuId, courseId);
        return courseLinkDao.save(courseLink);
    }

}
