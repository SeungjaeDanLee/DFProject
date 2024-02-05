package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.example.util")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/members/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/members/logout")
                .permitAll();

        http
                .authorizeRequests()
                .antMatchers("/admin/**").access("hasAuthority('ROLE_ADMIN')")
                .antMatchers("/members/**", "/board/**").access("hasRole('ROLE_USER')")
                .antMatchers("/", "/members/login", "/members/new").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT id, password, 1 as enabled FROM member WHERE id=?")
                .authoritiesByUsernameQuery("SELECT id, CASE WHEN member_level = 0 THEN 'ROLE_ADMIN' WHEN member_level = 1 THEN 'ROLE_USER' ELSE 'ROLE_USER' END as authority FROM member WHERE id=?");
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        return authorities -> {
            Set<String> mappedAuthorities = authorities.stream()
                    .map(authority -> {
                        int memberLevel = Integer.parseInt(authority.getAuthority());
                        if (memberLevel == 0) {
                            return "ROLE_ADMIN";
                        } else if (memberLevel == 1) {
                            return "ROLE_USER";
                        }
                        // 여러 권한 레벨에 대한 추가 로직을 필요에 따라 구현할 수 있습니다.
                        return null;
                    })
                    .filter(authority -> authority != null)
                    .collect(Collectors.toSet());
            return AuthorityUtils.createAuthorityList(mappedAuthorities.toArray(new String[0]));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
