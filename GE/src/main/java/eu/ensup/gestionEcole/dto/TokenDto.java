package eu.ensup.gestionEcole.dto;

import lombok.*;

import java.util.Date;

/**
 * The type Token dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String token;
    private String expireDate;
}
