package com.expensetracker.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/error").permitAll()
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/expenses/create").permitAll()
                .antMatchers("/api/categories/category").permitAll()
                .antMatchers("/api/categories").permitAll()
                .antMatchers("/api/expenses").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/categories/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/expenses/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/users/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/categories/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/expenses/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/users/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/categories/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/expenses/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build());
        return manager;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }



    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowUrlEncodedPeriod(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        firewall.setAllowBackSlash(true);
        firewall.setAllowSemicolon(true);
        firewall.setAllowUrlEncodedPeriod(true);
        firewall.setAllowUrlEncodedLineFeed(true);
        return firewall;
    }


    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }


}
