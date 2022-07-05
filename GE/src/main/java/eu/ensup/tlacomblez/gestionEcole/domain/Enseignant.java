package eu.ensup.tlacomblez.gestionEcole.domain;

import java.time.LocalDate;

import eu.ensup.tlacomblez.gestionEcole.Enums.Matiere;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity

/**
 * An "Enseignant" is personne that teache to student
 */
public class Enseignant extends Personne {

    private Matiere matiere_ens;

    // It's a constructor without id
    public Enseignant(String nom, String prenom, String email, String adresse, String telephone, LocalDate dateNaissance, Matiere matiere_ens) {
        super(nom, prenom, email, adresse, telephone, dateNaissance);
        this.matiere_ens = matiere_ens;
    }

    // It's a constructor with id
    public Enseignant(Long id, String nom, String prenom, String email, String password, String adresse, String telephone, LocalDate dateNaissance, Matiere matiere_ens ) {
        super(id, nom, prenom, email, adresse, telephone, dateNaissance);
        this.matiere_ens = matiere_ens;
    }
    
}
