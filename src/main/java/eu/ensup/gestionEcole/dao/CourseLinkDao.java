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

    /**
     * Find by id course list.
     *
     * @param id the id
     * @return the list
     */
    List<CourseLink> findByIdCourse(Long id);

    /**
     * Delete all by id student.
     *
     * @param uuid the uuid
     */
    void deleteAllByIdStudent(String uuid);
}
