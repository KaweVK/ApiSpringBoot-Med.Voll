package med.voll.api.domain.medico;
//record é uma "classe" imutável que já cria get, setter, etc de cada atributo da classe
//dto
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

//cada parametro deve ter o mesmo nome do que é passado como nome no request do insomnia
public record DadosCadastrosMedico(
        @NotBlank //uso de bin validation para verificar se o conteúdo do parâmetro abaixo não é nulo ou vazio. Só usado para Strings
        String nome,
        @NotBlank
        @Email //uso de bin validation para verificar se o conteúdo do parâmetro abaixo é um email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //uso de bin validation para verificar se o conteúdo do parâmetro abaixo está na difinição da expressão relugar. Nesse caso, dígitos (\\d), de 4 a 6 numeros
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid //uso de bin validation para verificar se o conteúdo do parâmetro abaixo, que deve ser um dto, tbm tem validações a serem feitas
        DadosEndereco endereco) {
}
