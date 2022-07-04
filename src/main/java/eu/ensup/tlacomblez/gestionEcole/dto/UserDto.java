package eu.ensup.tlacomblez.gestionEcole.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import eu.ensup.tlacomblez.gestionEcole.Enums.Roles;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 * A DTO (Data Transfer Object) class.
 */
public class UserDto {
    private Long id;
    private String username;
    private String lastName;
    private String email;
    private String address;
    private Roles role;
    private String password;
    private String picture;
    private Boolean activate;
}
