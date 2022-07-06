package eu.ensup.gestionEcole.dao;


import eu.ensup.gestionEcole.domain.CourseLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Course link dao.
 */
public interface CourseLinkDao extends JpaRepository<CourseLink, Long> {
    /**
     * Find by id student list.
     *
     * @param uuid the uuid
     * @return the list
     */
    List<CourseLink> findByIdStudent(String uuid);
}
