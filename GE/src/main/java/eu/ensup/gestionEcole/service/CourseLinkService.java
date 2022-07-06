package eu.ensup.gestionEcole.service;

import eu.ensup.gestionEcole.dao.CourseLinkDao;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.CourseLink;
import eu.ensup.gestionEcole.dao.EtudiantDao;
import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The type Course link service.
 */
@Service
@Transactional
public class CourseLinkService {

    /**
     * The Course link dao.
     */
    @Autowired
    CourseLinkDao courseLinkDao;

    /**
     * Associate course link.
     *
     * @param etudiantUuId the etudiant uu id
     * @param courseId     the course id
     * @return the course link
     */
    public CourseLink associate(String etudiantUuId, Long courseId){
        CourseLink courseLink = new CourseLink(null, etudiantUuId, courseId);
        return courseLinkDao.save(courseLink);
    }

    /**
     * Get courseof student list.
     *
     * @param uuid the uuid
     * @return the list
     */
    public List<CourseLink> getCourseofStudent(String uuid){
        return courseLinkDao.findByIdStudent(uuid);
    }

    /**
     * Get all link list.
     *
     * @return the list
     */
    public List<CourseLink> getAllLink(){
        return courseLinkDao.findAll();
    }

}
