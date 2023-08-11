package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // para personalizar as configurações de segurança
public class SecurityConfigurations {

    @Bean // anotação para devolver um objeto para o Spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable() // desativa a proteção contra csrf, pois o próprio JWT já faz
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // sessionManagent para definir o tipo de sessão, como é uma api rest deve ser stateless
                .and().build();
    }

}
