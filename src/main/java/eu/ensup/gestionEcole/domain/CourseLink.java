package eu.ensup.gestionEcole.domain;

import lombok.*;

import javax.persistence.*;

/**
 * The type Course link.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="CourseLink")
public class CourseLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idStudent;
    private Long idCourse;
}
