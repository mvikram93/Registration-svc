package com.alchemy.registration.config;

import com.alchemy.registration.roles.ERoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.alchemy.registration.roles.ApplicationUserPermission.*;
import static com.alchemy.registration.roles.ERoles.*;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
       // http.cors().and().csrf().disable();
      /*http
              .csrf().disable()
              .authorizeRequests()
              .antMatchers("/user/register")
              .permitAll().anyRequest().authenticated().and().httpBasic();*/
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/admin/*").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.POST,"/user/register").hasAuthority(USER_ADD.getPermission())
                .antMatchers(HttpMethod.DELETE,"/admin/**").hasAuthority(USER_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT,"/admin/**").hasAnyAuthority(USER_MODIFY.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();



    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails alchemy = User.builder().username("alchemy")
                .password(passwordEncoder.encode("alchemy"))
                //.roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails vikram = User.builder().username("vikram")
                .password(passwordEncoder.encode("vikram"))
                //.roles(ADMIN.name())
                .authorities(MODERATOR.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                alchemy,
                vikram
        );
    }

}
