package eu.ensup.gestionEcole.service;

import eu.ensup.gestionEcole.dao.CourseLinkDao;
import eu.ensup.gestionEcole.domain.CourseLink;
import eu.ensup.gestionEcole.dao.EtudiantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseLinkService {

    @Autowired
    CourseLinkDao courseLinkDao;

    @Autowired
    EtudiantDao etudiantRepository;
    public CourseLink associate(String etudiantUuId, Long courseId){
        CourseLink courseLink = new CourseLink(null, etudiantUuId, courseId);
        return courseLinkDao.save(courseLink);
    }

}
