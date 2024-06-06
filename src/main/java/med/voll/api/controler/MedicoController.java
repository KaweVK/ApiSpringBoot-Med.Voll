package med.voll.api.controler;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired //diz que será o próprio programa a instranciar o repository
    private MedicoRepository repository;

    @PostMapping //anotação para enviar/cadastrar na api
    @Transactional //anotação para dizer que há uma escrita de dados sendo feita
    public void cadastrar(@RequestBody @Valid DadosCadastrosMedico dados) { //RequestBody é pra dizer que tem que o parâmetro vai puxar o corpo inteiro da requisição
        repository.save(new Medico(dados)); //método para salvar
    }

    @GetMapping //anotação para pegar informação
    public Page<DadosListagemMedicos> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { //Page já é um objeto que retorna a lista e informações de paginação, e o objeto Pageable como parâmetro vai garantir que haja a paginação. A anotação Pageabledefault serve para que seja definida um padrão próprio para nossa api
        return repository.findAll(paginacao).map(DadosListagemMedicos::new); //findAll retorna um Page de Medico, então pegamos e mapeamos para converter para DadosListagemMedico
    }

    @PutMapping //anotação para atualizar informação
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id()); //aqui eu pego o objeto ao qual o id referencia
        medico.atualizarInformacoes(dados); //aqui chamo os métodos necessários para que seja atualizado
    }

    @DeleteMapping("/{id}") //anotação para excluir, e dentro dos parênteses eu posso colocar a parte da url dinâmica, sendo entre chaves para que ele saiba que vai ser um número
    @Transactional
    public void excluir(@PathVariable Long id) { //PathVariable é uma anotação que diz que o parâmetro deve ser retirado da url
        repository.deleteById(id);
    }
}
