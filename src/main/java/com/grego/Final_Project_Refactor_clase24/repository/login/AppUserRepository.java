package com.grego.Final_Project_Refactor_clase24.repository.login;


import com.grego.Final_Project_Refactor_clase24.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {


    Optional<AppUser> findByEmail(String email);

    @Override
    boolean existsById(Integer integer);

    //FIND BY EMAIL OR USERNAME
    @Query("SELECT a FROM AppUser a WHERE a.email = ?1 OR a.username = ?1")
    Optional<AppUser> findByEmailOrUsername(String emailOrUsername);


}




