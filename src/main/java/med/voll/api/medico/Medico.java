package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded //fica numa classe separada, mas os seus campos fazem parte da mesma tabela de medicos
    private Endereco endereco;

}
