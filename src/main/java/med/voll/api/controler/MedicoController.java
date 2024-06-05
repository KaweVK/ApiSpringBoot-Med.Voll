package med.voll.api.controler;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastrosMedico;
import med.voll.api.medico.DadosListagemMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired //diz que será o próprio programa a instranciar o repository
    private MedicoRepository repository;

    @PostMapping //post é para enviar na api
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastrosMedico dados) { //RequestBody é pra dizer que tem que o parâmetro vai puxar o corpo inteiro da requisição
        repository.save(new Medico(dados)); //método para salvar
    }

    @GetMapping //anotação para pegar informação
    public List<DadosListagemMedicos> listar() {
        return repository.findAll().stream().map(DadosListagemMedicos::new).toList(); //findAll retorna Medico, então pegamos e mapeamos para converter em uma lista de DadosListagemMedico
    }
}
