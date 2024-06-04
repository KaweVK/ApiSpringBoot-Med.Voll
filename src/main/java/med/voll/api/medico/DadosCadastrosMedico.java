package med.voll.api.medico;
//record é uma "classe" imutável que já cria get, setter, etc de cada atributo da classe
//dto
import med.voll.api.endereco.DadosEndereco;

//cada parametro deve ter o mesmo nome do que é passado como nome no request do insomnia
public record DadosCadastrosMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
