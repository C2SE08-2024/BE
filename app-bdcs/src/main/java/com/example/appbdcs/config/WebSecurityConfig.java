package com.example.appbdcs.config;

import com.example.appbdcs.security.jwt.JwtEntryPoint;
import com.example.appbdcs.security.jwt.JwtFilter;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailService;

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
                        "/api/v1/course/**",
                        "/api/v1/course-day/**",
                        "/api/v1/course-type/**",
                        "/api/v1/comment/**",
                        "/api/v1/day/**",
                        "/api/v1/payment/**",
                        "/api/v1/cart/**")
                .permitAll()
                .antMatchers("/api/v1/student/**").hasAnyRole("ROLE_STUDENT")
                .antMatchers("/api/v1/instructor/**").hasAnyRole("ROLE_INSTRUCTOR")
                .antMatchers("/api/v1/business/**").hasAnyRole("ROLE_BUSINESS")
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
