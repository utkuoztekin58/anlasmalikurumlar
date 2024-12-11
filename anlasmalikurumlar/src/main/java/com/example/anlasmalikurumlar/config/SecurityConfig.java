package com.example.anlasmalikurumlar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;


    @Autowired
    public SecurityConfig(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer
                        .ignoringRequestMatchers("/categories/save")
                        .ignoringRequestMatchers("/categories/delete")
                        .ignoringRequestMatchers("/categories/update")
                        .ignoringRequestMatchers("/companies/update")
                        .ignoringRequestMatchers("/companies/save")
                        .ignoringRequestMatchers("/companies/delete/**")
                        .ignoringRequestMatchers("/categories/delete/**")
                        .ignoringRequestMatchers("/communications/delete/**")
                        .ignoringRequestMatchers("/edit/company")
                        .ignoringRequestMatchers("/user/companies/filter")
                        .ignoringRequestMatchers("/documents/save")
                        .ignoringRequestMatchers("/documents/delete/**")
                        .ignoringRequestMatchers("/documents/update")
                        .ignoringRequestMatchers("/edit")
                        .ignoringRequestMatchers("/logout")



                )
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/admin").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/categories/new/**").permitAll()
                        .requestMatchers("/companies/**").permitAll()
                        .requestMatchers("/new_company/**").permitAll()
                        .requestMatchers("/edit_company/**").permitAll()
                        .requestMatchers("/companies/**").permitAll()
                        .requestMatchers("/communications/delete/**").hasAnyRole("ADMIN")
                        .requestMatchers("/companies/update").permitAll()
                        .requestMatchers("/documents/delete/**").hasAnyRole("ADMIN")
                        .requestMatchers("/companies/delete/**").hasAnyRole("ADMIN")
                        .requestMatchers("/categories/delete/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/categories/save").permitAll()
                        .requestMatchers("/user/companies/**").permitAll()


                        .requestMatchers("/documents/**").permitAll()
                        .anyRequest().authenticated()


                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .failureUrl("/login?error=true")
                        .successHandler(new CustomAuthenticationSuccessHandler())
                )
                .authenticationManager(authenticationManager)
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }


}

