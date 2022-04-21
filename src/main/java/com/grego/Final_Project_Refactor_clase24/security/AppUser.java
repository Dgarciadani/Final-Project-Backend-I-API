package com.grego.Final_Project_Refactor_clase24.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(name = "appUserSeq", sequenceName = "APP_USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appUserSeq")
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)

    private AppUserRoles appUserRoles;


    public AppUser() {
    }

    public AppUser(String name, String username, String password, String email, AppUserRoles appUserRoles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.appUserRoles = appUserRoles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRoles.name());
        return Collections.singletonList(authority);
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

}
