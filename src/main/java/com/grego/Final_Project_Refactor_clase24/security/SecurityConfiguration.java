package com.grego.Final_Project_Refactor_clase24.security;


import com.grego.Final_Project_Refactor_clase24.services.impl.login.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private AppUserService appUserService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /* -------by me-------
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login/**").permitAll()
        *//*        .antMatchers(HttpMethod.GET, "/patient/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/dentist/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/appointment/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/dentist/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().loginPage("/login")
                .and().logout();*//*
    }*/


   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()


                .antMatchers("/auth/**").permitAll()
                .antMatchers("/patient/**").hasAuthority("USER")
                .antMatchers("/dentist/**").hasAuthority("USER")
                .antMatchers("/appointment/**").hasAuthority("USER")
                .and().formLogin().loginPage("/login")
                .and().logout();


    }*/


 //  ------ Other By me ------
   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin()
                .loginPage("/login");
    }


    /* ------ by pablo-------
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/dentist/**", "/patient/**", "/appointment/** ")
                .hasAuthority("ROLE_ADMIN")
                .antMatchers("/patient/")
                .hasAuthority("ROLE_USER")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();
    }*/


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(appUserService);
        return daoAuthenticationProvider;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
