package com.hust.tacosapi.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    // ghi đè phương thức configure ở trong SecurityConfig class
//    @Autowired
//    private DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("anhyeuem");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/","/**").access("permitAll");
        http.csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("buzz")
//                    .password("infinity")
//                    .authorities("ROLO_USER")
//                .and()
//                .withUser("woody")
//                    .password("bullseye")
//                    .authorities("ROLE_USER");


//         auth
//            .jdbcAuthentication()
//            .dataSource(dataSource)
//            .usersByUsernameQuery(
//                    "select username, password, enabled from Users " +
//                            "where username=?")
//            .authoritiesByUsernameQuery(
//                    "select username, authority from UserAuthorities " +
//                            "where username=?");
            auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

    }
}


