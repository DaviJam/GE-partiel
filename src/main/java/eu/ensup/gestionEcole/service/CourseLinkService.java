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

@Service
@Transactional
public class CourseLinkService {

    @Autowired
    CourseLinkDao courseLinkDao;

    public CourseLink associate(String etudiantUuId, Long courseId){
        CourseLink courseLink = new CourseLink(null, etudiantUuId, courseId);
        return courseLinkDao.save(courseLink);
    }

    public List<CourseLink> getCourseofStudent(String uuid){
        return courseLinkDao.findByIdStudent(uuid);
    }

    public List<CourseLink> getAllLink(){
        return courseLinkDao.findAll();
    }

    public void deleteCourseLink(String uuid){ courseLinkDao.deleteAllByIdStudent(uuid);}

    public List<CourseLink> getBySource(Long id) {
        return this.courseLinkDao.findByIdCourse(id);
    }
}
