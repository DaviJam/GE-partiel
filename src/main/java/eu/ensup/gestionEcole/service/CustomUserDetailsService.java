package eu.ensup.gestionEcole.service;

import eu.ensup.gestionEcole.dao.DirecteurDao;
import eu.ensup.gestionEcole.domain.CustomUserDetails;
import eu.ensup.gestionEcole.domain.Directeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * It implements the UserDetailsService interface and overrides the loadUserByUsername() method
 */
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private DirecteurDao directeurDao;

    @Override
    // A method that is called by Spring Security to load the user details.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Directeur userD = directeurDao.findByEmail(username);
        if (userD != null) {
            
            return new CustomUserDetails(userD);
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
 
}