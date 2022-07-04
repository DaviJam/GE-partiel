package eu.ensup.tlacomblez.gestionEcole.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import eu.ensup.tlacomblez.gestionEcole.Enums.Roles;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
/**
 * It creates a class called Responsable that extends the Personne class.
 */
public class Responsable extends Personne {
    private String password;
    private Roles role;
    
    public Responsable(String nom, String prenom, String email, String password, String adresse, String telephone, LocalDate dateNaissance) {
        super(nom, prenom, email, adresse, telephone, dateNaissance);
        this.password = password;
        this.role = Roles.DIRECTEUR;
    }

    public Responsable(Long id, String nom, String prenom, String email, String password, String adresse, String telephone, LocalDate dateNaissance) {
        super(id, nom, prenom, email, adresse, telephone, dateNaissance);
        this.password = password;
        this.role = Roles.RESPONSABLE;
    }

    public Responsable() {
        super();
    }
}
