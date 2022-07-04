package eu.ensup.gestionEcole.service;

import eu.ensup.gestionEcole.dao.EcoleDao;
import eu.ensup.gestionEcole.domain.Ecole;
import eu.ensup.gestionEcole.dto.EcoleDto;
import eu.ensup.gestionEcole.mapper.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EcoleService {

    @Autowired
    EcoleDao ecoleDao;

    @Autowired
    CustomMapper customMapper;

    Ecole create(EcoleDto dto){
        return ecoleDao.save(customMapper.ecoleDtoToEcole(dto));
    }
}
