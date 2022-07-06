package eu.ensup.gestionEcole.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Link course dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkCourseDTO {
    private String idStudent;
    private Long idCourse;
}
