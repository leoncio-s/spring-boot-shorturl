package com.leoncio.shorturl.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebConfig {
    @Bean
    protected SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        return http
        
        .cors(withDefaults()).csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((requests) -> requests
            .antMatchers(HttpMethod.GET, "/").permitAll()
            .antMatchers(HttpMethod.POST, "/").permitAll()
            .antMatchers("/h2-console/**").hasRole("ADMIN")
            .antMatchers("/swagger-ui/**").permitAll()
        ).sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    }

    private Customizer<CorsConfigurer<HttpSecurity>> withDefaults() {
        return new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> t) {
                t.disable();
            }
        };
    }

    @Bean
    public ServletRegistrationBean<javax.servlet.Servlet> h2servletRegistration(){
        ServletRegistrationBean<javax.servlet.Servlet> servletRegistrationBean = new ServletRegistrationBean<javax.servlet.Servlet>(new org.h2.server.web.WebServlet());
        servletRegistrationBean.addUrlMappings("/h2-console/**");
        return servletRegistrationBean;
    }

    @Bean
    public Logger log4j(){
        return LogManager.getLogger();
    }

}
