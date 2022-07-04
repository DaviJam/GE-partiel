package eu.ensup.tlacomblez.gestionEcole.domain;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import eu.ensup.tlacomblez.gestionEcole.Enums.Roles;
import lombok.Getter;
import lombok.Setter;   

@Getter
@Setter
@Entity

/**
 * Directeur is a Personne
 */
public class Directeur extends Personne {
    private String password;
    private Roles role;

    public Directeur(String nom, String prenom, String email, String password, String adresse, String telephone, LocalDate dateNaissance) {
        super(nom, prenom, email, adresse, telephone, dateNaissance);
        this.password = password;
        this.role = Roles.DIRECTEUR;
    }

    public Directeur(Long id, String nom, String prenom, String email, String password, String adresse, String telephone, LocalDate dateNaissance) {
        super(id, nom, prenom, email, adresse, telephone, dateNaissance);
        this.password = password;
        this.role = Roles.DIRECTEUR;
    }

    public Directeur() {
        super();
    }

}
