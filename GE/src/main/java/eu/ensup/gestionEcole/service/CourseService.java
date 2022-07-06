package eu.ensup.gestionEcole.service;

import eu.ensup.gestionEcole.dao.CourseDao;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The type Course service.
 */
@Service
@Transactional
public class CourseService {
    /**
     * The Course repository.
     */
    @Autowired
    CourseDao courseRepository;

    /**
     * Gets course.
     *
     * @return the course
     */
    public List<Cours> getallCourse() {
        return courseRepository.findAll();
    }
}
