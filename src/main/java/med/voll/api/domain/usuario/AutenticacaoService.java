package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //anotação para dizer ao spring que é para carregar essa classe e que ela executa algum serviço
public class AutenticacaoService implements UserDetailsService { //essa interface faz com que o spring saiba que essa é a classe que ele deve chamar na hora de autenticar

    @Autowired //dependencia
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

}
