package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired //diz que será o próprio programa a instranciar o repository
    private MedicoRepository repository;

    @PostMapping //anotação para enviar/cadastrar na api
    @Transactional //anotação para dizer que há uma escrita de dados sendo feita
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastrosMedico dados, UriComponentsBuilder uribuilder) { //RequestBody é pra dizer que tem que o parâmetro vai puxar o corpo inteiro da requisição
        var medico = new Medico(dados);
        repository.save(medico); //método para salvar
        var uri = uribuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); //ruiBuilder cria a uri sem precisar sabermos qual é, basta colocar o complemento específico que identifica que isso deve ocorrer

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico)); //código 201 é para registrar um created, e deve devolver os dados do novo recurso/registro criado, e o cabeçaljo do protocolo HTTP chamado location
    }

    @GetMapping //anotação para pegar informação
    public ResponseEntity<Page<DadosListagemMedicos>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { //Page já é um objeto que retorna a lista e informações de paginação, e o objeto Pageable como parâmetro vai garantir que haja a paginação. A anotação Pageabledefault serve para que seja definida um padrão próprio para nossa api
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new); //findAllByAtivoTrue retorna um Page de Medico que tenha a column Ativo como True, então pegamos e mapeamos para converter para DadosListagemMedico
        return ResponseEntity.ok(page);
    }

    @PutMapping //anotação para atualizar informação
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id()); //aqui eu pego o objeto ao qual o id referencia
        medico.atualizarInformacoes(dados); //aqui chamo os métodos necessários para que seja atualizado

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); //ok é código 200
    }

    @DeleteMapping("/{id}") //anotação para excluir, e dentro dos parênteses eu posso colocar a parte da url dinâmica, sendo entre chaves para que ele saiba que vai ser um número
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) { //PathVariable é uma anotação que diz que o parâmetro deve ser retirado da url
        var medico = repository.getReferenceById(id);
        medico.excluir(); //chamo o método que setta ativo como false

        return ResponseEntity.noContent().build(); //Retornando ResponseEntity, podemos devolver um códigop diferente do 200 em cada situação. No content é o código 204, e o build retorna um objeto ResponseEntity
    }

    @GetMapping("/{id}") //anotação para excluir, e dentro dos parênteses eu posso colocar a parte da url dinâmica, sendo entre chaves para que ele saiba que vai ser um número
    public ResponseEntity detalhar(@PathVariable Long id) { //PathVariable é uma anotação que diz que o parâmetro deve ser retirado da url
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); //Retornando ResponseEntity, podemos devolver um códigop diferente do 200 em cada situação. No content é o código 204, e o build retorna um objeto ResponseEntity
    }
}
