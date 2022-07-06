package eu.ensup.gestionEcole.domain;

import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * The type Custom user details.
 */
public class CustomUserDetails implements UserDetails {
 
    private Directeur directeur;


    /**
     * Instantiates a new Custom user details.
     *
     * @param directeur the directeur
     */
    public CustomUserDetails(Directeur directeur) {
        this.directeur = directeur;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
 
    @Override
    public String getPassword() {
        if (directeur != null) {
            return directeur.getPassword();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
       
    }
 
    @Override
    public String getUsername() {
       
        if (directeur != null) {
            return directeur.getEmail();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        if (directeur != null) {
            return directeur.getEmail();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
        
    }
 
}
