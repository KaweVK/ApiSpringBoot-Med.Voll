package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if (!pacienteAtivo) {
            throw new ValidacaoException("Paciente inativo");
        }
    }

}
