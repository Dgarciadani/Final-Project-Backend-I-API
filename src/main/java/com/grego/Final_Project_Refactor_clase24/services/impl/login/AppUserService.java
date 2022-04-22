package com.grego.Final_Project_Refactor_clase24.services.impl.login;
import com.grego.Final_Project_Refactor_clase24.dto.AppUserDTO;
import com.grego.Final_Project_Refactor_clase24.repository.login.AppUserRepository;
import com.grego.Final_Project_Refactor_clase24.security.AppUser;
import com.grego.Final_Project_Refactor_clase24.security.AppUserRoles;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final static Logger logger = Logger.getLogger(AppUserService.class);
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Autowired
    private ModelMapper modelmapper;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmailOrUsername(email).orElseThrow((() -> new UsernameNotFoundException("User or email not found")));
    }

    public void registerUser(AppUserDTO appUser) {
        if (appUserRepository.findByEmail(appUser.getEmail()).isPresent()) {
            logger.error("User already exists");
            throw new IllegalArgumentException("User already exists");
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            AppUser newUser = mapToEntity(appUser);
            newUser.setAppUserRoles(AppUserRoles.USER);
            String newPass = bCryptPasswordEncoder.encode(appUser.getPassword());
            newUser.setPassword(newPass);
            appUserRepository.save(newUser);
        }
    }


    //---Mapper---
    public AppUserDTO mapToDTO(AppUser appUser) {
        return modelmapper.map(appUser, AppUserDTO.class);
    }

    public AppUser mapToEntity(AppUserDTO appUser) {
        return modelmapper.map(appUser, AppUser.class);
    }


}

