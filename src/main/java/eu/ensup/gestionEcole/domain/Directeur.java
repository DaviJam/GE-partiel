package eu.ensup.gestionEcole.domain;


import javax.persistence.*;

import lombok.*;

/**
 * The type Directeur.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "Directeur")

/**
 * Directeur is a Personne
 */
public class Directeur {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
}
