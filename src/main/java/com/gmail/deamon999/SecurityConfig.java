package com.gmail.deamon999;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/images/**").permitAll()
                .antMatchers("/", "/register", "/newuser", "/previous/", "/next/").permitAll().anyRequest().authenticated()
                .antMatchers("/createCategory", "/category/delete", "/message/delete", "/subject/delete").hasAnyRole("ADMIN")
                .antMatchers("/newCategory",
                        "/message",
                        "/newMessage",
                        "/search",
                        "/cabinet",
                        "/roleAdmin",
                        "/roleUser",
                        "/userDetails",
                        "/change",
                        "/details",
                        "/changeEmail",
                        "/changeDetails",
                        "/privateMessage/delete",
                        "/createPrivateMessage",
                        "/newPrivateMessage",
                        "/subject",
                        "/newSubject",
                        "/createMessage",
                        "/createSubject").hasAnyRole("ADMIN", "USER")

                .and()
                .exceptionHandling()
                .accessDeniedPage("/unauthorized")
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);
    }

    private ShaPasswordEncoder getShaPasswordEncoder() {
        return new ShaPasswordEncoder();
    }
}
