package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.TokenService;
import med.voll.api.infra.security.DadosTokenJWT;
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

    @Autowired
    private AuthenticationManager manager; //classe própria do spring security que deve ser usada para chamar o service de autenticação por debaixo dos panos
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var Authenticationtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); //UsernamePasswordAuthenticationToken é um dto próprio do spring, convertendo do nosso dto para ele
        var authentication = manager.authenticate(Authenticationtoken); //representa o objeto que tem a autorização do token, tem o usuário

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
