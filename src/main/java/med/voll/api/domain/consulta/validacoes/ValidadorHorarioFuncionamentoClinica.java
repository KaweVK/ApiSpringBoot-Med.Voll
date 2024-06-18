package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

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
