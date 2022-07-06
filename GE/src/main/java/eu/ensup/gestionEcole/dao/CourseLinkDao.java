package eu.ensup.gestionEcole.dao;


import eu.ensup.gestionEcole.domain.CourseLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseLinkDao extends JpaRepository<CourseLink, Long> {
    List<CourseLink> findByIdStudent(String uuid);
}
