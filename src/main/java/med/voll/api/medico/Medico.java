package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;

//JPA
@Table(name = "medicos")
@Entity(name = "Medico")
//anotações lombok ->
@Getter //gera (em tempo de execução) todos os getters
@NoArgsConstructor //gera (em tempo de execução) um construtor que não recebe nenhum argumento
@AllArgsConstructor //gera (em tempo de execução) um construtor que recebe todos os argumentos
@EqualsAndHashCode(of = "id") //gera (em tempo de execução) um equal/hashcode esppecificamente verificando o id
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded //fica numa classe separada, mas os seus campos fazem parte da mesma tabela de medicos
    private Endereco endereco;

    private boolean ativo;

    public Medico(DadosCadastrosMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
