package med.voll.api.infra.security;
//Um filtro é usado para fazer uma verificação antes de prosseguir para o resto do código, seja outro filtro, ou um controller

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //Carrega automaticamente, mas diz que é um componente genérico
public class SecurityFilter extends OncePerRequestFilter {//com o OncePer, defino que a classe é um filtro

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        filterChain.doFilter(request, response); //continua o fluxo da requisição
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization"); //recupera o cabeçalho Authorization
        if (authorizationHeader == null) {
            throw new RuntimeException("Token JWT não enviado no cabeçalho");
        }

        return authorizationHeader.replace("Bearer ", ""); //retirar o prefixo bearer
    }

}
