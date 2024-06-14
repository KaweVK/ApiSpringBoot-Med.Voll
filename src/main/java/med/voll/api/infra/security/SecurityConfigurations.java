package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //para dizer que é uma classe de configuração e deve ser carregada
@EnableWebSecurity //indica que vamos personalizar as configurações de segurança
public class SecurityConfigurations { //Aqui vamos colocar as configurações do Spring Security

    @Autowired
    private SecurityFilter securityFilter;

    @Bean //serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realize a sua injeção de dependência em outras classes.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable() //desabilita proteção contra ataques do tipo Cross-Site Request Forgery
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll() // diz que se houver uma requisição post na url de login, devo autorizar independendte de tudo
                .anyRequest().authenticated() //de resto, tenho que verificar
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //aqui eu digo que o meu filtro deve ser chamado antes do segundo filtro, pois sem isso pode acarentar em erro na nossa aplicação
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //método para dizer ao Spring qual algoritmo de hash de senha utilizar
        return new BCryptPasswordEncoder();
    }
}
