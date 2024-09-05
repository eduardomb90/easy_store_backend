package br.com.marques.easystore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class MyWebApplicationSecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        System.out.println("-------> CONFIGURANDO ACESSOS.");

        http
            .cors(withDefaults())
            .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(
                    (authz) -> authz
                                    //requisições liberadas
                                    .requestMatchers(HttpMethod.GET, "/categorias").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/categorias/search").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/cliente/*").permitAll()
                                    .requestMatchers(HttpMethod.POST, "/pedido").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/produto").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/produto/categoria/*").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/produto/busca").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/produto/*").permitAll()
                                    .requestMatchers(HttpMethod.POST, "/login").permitAll()

                                    //.anyRequest().permitAll()     //usado para testes
                                    .anyRequest().authenticated()
                );

        http
            .addFilterBefore(new WebSecurityFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
