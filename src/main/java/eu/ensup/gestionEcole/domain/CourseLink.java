package eu.ensup.gestionEcole.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="CourseLink")
public class CourseLink {
    @Id
    private Long id;
    private String idStudent;
    private Long idCourse;
}
