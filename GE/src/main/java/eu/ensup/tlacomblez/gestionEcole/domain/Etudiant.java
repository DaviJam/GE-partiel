package eu.ensup.tlacomblez.gestionEcole.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity

/**
 * Etudiant is a student and he is a Personne
 */
public class Etudiant extends Personne {

    @ManyToMany

    private Set<Cours> listCours;

    @ManyToOne
    // A list of Cours
    private Ecole ecole;

    public Etudiant(String nom, String prenom, String email, String adresse, String telephone, LocalDate dateNaissance) {
        super(nom, prenom, email, adresse, telephone, dateNaissance);
    }
}  
