package med.voll.api.controler;

import med.voll.api.medico.DadosCadastrosMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired //diz que será o próprio programa a instranciar o repository
    private MedicoRepository repository;

    @PostMapping //post é para enviar na api
    @Transactional
    public void cadastrar(@RequestBody DadosCadastrosMedico dados) { //RequestBody é pra dizer que tem que o parâmetro vai puxar o corpo inteiro da requisição
        repository.save(new Medico(dados)); //método para salvar
    }

}
