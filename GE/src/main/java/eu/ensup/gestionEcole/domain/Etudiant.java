package eu.ensup.gestionEcole.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;

/**
 * The type Etudiant.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="Etudiant")
public class Etudiant {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String telephone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
}  
