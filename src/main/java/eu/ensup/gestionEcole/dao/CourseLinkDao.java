package eu.ensup.gestionEcole.dao;

import eu.ensup.gestionEcole.domain.CourseLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseLinkDao extends JpaRepository<CourseLink, Long> {
}
