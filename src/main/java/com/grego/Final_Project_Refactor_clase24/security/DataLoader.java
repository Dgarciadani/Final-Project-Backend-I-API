package com.grego.Final_Project_Refactor_clase24.security;

import com.grego.Final_Project_Refactor_clase24.repository.login.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader  implements ApplicationRunner {

    private AppUserRepository appUserRepository;

    @Autowired
    public DataLoader(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder bCcryptPasswordEncoder1 = new BCryptPasswordEncoder();
        String password1 = bCcryptPasswordEncoder1.encode("password");
        String password2 = bCcryptPasswordEncoder1.encode("password");

        appUserRepository.save(new AppUser("Grego1","Gregogc1",password1,"dgarcia",AppUserRoles.ROLE_ADMIN));
        appUserRepository.save(new AppUser("Grego2","Gregogc2",password2,"dgarciadani@gmail2.com",AppUserRoles.ROLE_USER));
    }
}
