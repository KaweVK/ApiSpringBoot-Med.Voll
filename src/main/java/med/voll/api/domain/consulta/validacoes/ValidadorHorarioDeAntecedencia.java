package med.voll.api.domain.consulta.validacoes;
//essas classes de validação servem para que sejam verificadas as regras de negócio da nossa api, deixando mais fácil os testes e correções
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioDeAntecedencia {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var dataAgora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedencia mínima de 30 minutos");
        }

    }
}
