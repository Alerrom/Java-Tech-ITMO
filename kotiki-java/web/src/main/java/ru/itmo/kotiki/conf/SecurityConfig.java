package ru.itmo.kotiki.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public ApplicationContext context;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/all.html").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/username").permitAll()
                .antMatchers("/indexUser.html").access("hasRole('READER') or hasRole('USER')")
                .antMatchers("/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/user").hasRole("READER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        DataSource dataSource = (DataSource) context.getBean(DataSource.class);
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_naim as username, user_password as password, 1 as enabled from xt_users where user_naim=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_naim as username, case when upper(user_naim) like 'ADMIN%' then 'ROLE_ADMIN' else 'ROLE_USER' end as authority from xt_users where user_naim=?");

        System.out.println(passwordEncoder().encode("password100"));
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
