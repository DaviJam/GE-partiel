package eu.ensup.gestionEcole.service;

import eu.ensup.gestionEcole.dao.CourseDao;
import eu.ensup.gestionEcole.domain.Cours;
import eu.ensup.gestionEcole.domain.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseService {
    @Autowired
    CourseDao courseRepository;

    public List<Cours> getallCourse() {
        return courseRepository.findAll();
    }
}
