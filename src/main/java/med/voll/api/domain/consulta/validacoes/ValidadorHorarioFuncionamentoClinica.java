package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaClinicaAbrir = dataConsulta.getHour() < 7;
        var depoisDaClinaFechar = dataConsulta.getHour() > 18;

        if (domingo || antesDaClinicaAbrir || depoisDaClinaFechar) {
            throw new ValidacaoException("Consulta fora do horário de expediente da clínica");
        }
    }
}
