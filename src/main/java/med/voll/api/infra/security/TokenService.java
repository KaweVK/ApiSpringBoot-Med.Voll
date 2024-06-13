package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) { //método para gerar o token
        try {
            var algoritmo = Algorithm.HMAC256(secret); //cria o algoritmo que vamos usar e define a senha da api
            return JWT.create()
                    .withIssuer("API Voll.med") //identificador da api
                    .withSubject(usuario.getLogin()) //identificador do dono do token
                    .withClaim("id", usuario.getId()) //salvar uma informação numa chave com um valor
                    .withExpiresAt(dataExpiracao()) //deifini uma data de expiração
                    .sign(algoritmo); //faz a assinatura a partir do nosso algoritmo
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt");
        }
    }

    public String getSubject(String tokenJWT) { //passa o token e ele vericará se está válido e em seguida devolve o subject dentro do token
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT) // verifica se tá válido
                    .getSubject(); //pega o usuario
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now() //pego a data de agora
                .plusHours(2) //adiciono duas horas
                .toInstant(ZoneOffset.of("-03:00")); //converto para Instant passando o fuso horário
    }

}
