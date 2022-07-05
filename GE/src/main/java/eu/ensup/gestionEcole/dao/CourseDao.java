package eu.ensup.gestionEcole.dao;

import eu.ensup.gestionEcole.domain.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Cours, Long> {
    Cours findByTheme(String theme);
}
