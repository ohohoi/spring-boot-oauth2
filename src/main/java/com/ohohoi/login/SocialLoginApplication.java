package com.ohohoi.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication
public class SocialLoginApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SocialLoginApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests(a -> a
                    .antMatchers("/", "/error", "/webjars/**").permitAll()
                    .anyRequest().authenticated()
            )
            .exceptionHandling(e -> e
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .logout(l -> l
                    .logoutSuccessUrl("/").permitAll()
            )
            .csrf(c -> c
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            .oauth2Login();
        // @formatter:on
    }
}
