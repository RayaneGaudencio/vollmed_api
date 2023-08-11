package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired // anotação para o spring injetar o parâmetro
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); // DTO do spring
        var authentication = manager.authenticate(token); // retorna um objeto que representa o usuario autenticado no sistema
        // o próprio spring acha a classe AuthenticationService e dentro do método loadUserByUsername busca através do repository o usuário

        return ResponseEntity.ok().build();
    }

}
