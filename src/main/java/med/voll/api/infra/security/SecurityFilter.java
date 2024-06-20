package med.voll.api.infra.security;
//Um filtro é usado para fazer uma verificação antes de prosseguir para o resto do código, seja outro filtro, ou um controller

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //Carrega automaticamente, mas diz que é um componente genérico
public class SecurityFilter extends OncePerRequestFilter {//com o OncePer, defino que a classe é um filtro

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request); //pego o token

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT); //pego o login do usuario daquele token
            var usuario = repository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); //seto esse usuario como "logado"
        }

        filterChain.doFilter(request, response); //continua o fluxo da requisição
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization"); //recupera o cabeçalho Authorization
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", ""); //retirar o prefixo bearer
        }
        return null;
    }

}
