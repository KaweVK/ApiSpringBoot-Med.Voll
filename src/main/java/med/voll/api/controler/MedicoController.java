package med.voll.api.controler;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastrosMedico;
import med.voll.api.medico.DadosListagemMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public Page<DadosListagemMedicos> listar(Pageable paginacao) { //Page já é um objeto que retorna a lista e informações de paginação, e o objeto Pageable como parâmetro vai garantir que haja a paginação
        return repository.findAll(paginacao).map(DadosListagemMedicos::new); //findAll retorna um Page de Medico, então pegamos e mapeamos para converter para DadosListagemMedico
    }
}
