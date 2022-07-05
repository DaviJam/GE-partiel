package eu.ensup.tlacomblez.gestionEcole.domain;

import lombok.Getter;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

/**
 * It's a class that represents a person
 */
public class Personne {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String telephone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;

    public Personne(String nom, String prenom, String email, String adresse, String telephone, 
            LocalDate dateNaissance) {

       this.nom = nom;
       this.prenom = prenom;
       this.email = email;
       this.adresse = adresse;
       this.telephone = telephone;
       this.dateNaissance = dateNaissance;
    }

    public Personne(Long id2, String nom2, String prenom2, String email2, String adresse2, String telephone2,
            LocalDate dateNaissance2) {
        this.id = id2;
        this.nom = nom2;
        this.prenom = prenom2;
        this.email = email2;
        this.adresse = adresse2;
        this.telephone = telephone2;
        this.dateNaissance = dateNaissance2;
    }
  
}
