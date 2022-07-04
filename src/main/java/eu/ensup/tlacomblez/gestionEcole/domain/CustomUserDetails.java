package eu.ensup.tlacomblez.gestionEcole.domain;

import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class CustomUserDetails implements UserDetails {
 
    private Directeur directeur;

    private Responsable responsable;
     
    public CustomUserDetails(Directeur directeur) {
        this.directeur = directeur;
    }

    public CustomUserDetails(Responsable responsable ) {
        this.responsable = responsable;
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
        else if (responsable != null) {
            return responsable.getPassword();
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
        else if (responsable != null) {
            return responsable.getEmail();
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
     
    public String getFullName() {
        if (directeur != null) {
            return directeur.getNom() + " " + directeur.getPrenom();
        }
        else if (responsable != null) {
            return responsable.getNom() + " " + responsable.getPrenom();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
        
    }
 
}
