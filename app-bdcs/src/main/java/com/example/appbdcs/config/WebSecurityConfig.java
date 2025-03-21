package com.example.appbdcs.config;

import com.example.appbdcs.security.jwt.JwtEntryPoint;
import com.example.appbdcs.security.jwt.JwtFilter;
import com.example.appbdcs.security.userprinciple.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/v1/public/**",
                        "/api/v1/home/**",
                        "/api/v1/course/**",
                        "/api/v1/payment/**",
                        "/api/v1/job/**",
                        "/api/v1/cart/**",
                        "/api/v1/business/**",
                        "/api/v1/accounts")
                .permitAll()
                .antMatchers("/api/v1/lessons/**",
                        "/api/v1/tests/**",
                        "/api/v1/test-questions/**",
                        "/api/v1/student/**").hasAnyRole("STUDENT", "ADMIN", "INSTRUCTOR")
                .antMatchers("/api/v1/student-test-result/**").hasAnyRole("STUDENT")
                .antMatchers("/api/v1/instructor/**").hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers("/api/v1/business/managers/**").hasAnyRole("ADMIN", "BUSINESS")
                .antMatchers("/api/v1/business/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}