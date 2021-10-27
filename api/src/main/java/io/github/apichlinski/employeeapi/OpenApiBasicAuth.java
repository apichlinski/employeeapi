package io.github.apichlinski.employeeapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@OpenAPIDefinition(info = @Info(
        title = "EmployeeAPI",
        description = "",
        version = "v0.0.1",
        contact = @Contact(
                name = "Andrzej Pichliński",
                url = "https://github.com/apichlinski",
                email = "andrzej.pichlinski@gmail.com"
        )
),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Dev"
        )
)
class OpenApiBasicAuth extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").authenticated()
                .antMatchers("/swagger-ui/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("employeeapi")
                .password("{noop}S,nUSY5aa@Ru}<XU")
                .roles("USER");
    }
}