package eu.ensup.gestionEcole.dao;

import eu.ensup.gestionEcole.domain.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Course dao.
 */
public interface CourseDao extends JpaRepository<Cours, Long> {
    /**
     * Find by theme cours.
     *
     * @param theme the theme
     * @return the cours
     */
    Cours findByTheme(String theme);
}
