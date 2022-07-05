package eu.ensup.gestionEcole.dto;

import eu.ensup.gestionEcole.domain.Directeur;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EcoleDto {
    private String nom;
    private String email;
    private String adresse;
    private Long telephone;
    private Directeur directeur;
}
