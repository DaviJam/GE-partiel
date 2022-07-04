package eu.ensup.gestionEcole.mapper;

import eu.ensup.gestionEcole.domain.Ecole;
import eu.ensup.gestionEcole.dto.EcoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomMapper{
    public Ecole ecoleDtoToEcole(EcoleDto dto);
}
