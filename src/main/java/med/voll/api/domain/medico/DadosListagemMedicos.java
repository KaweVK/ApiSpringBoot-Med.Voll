package med.voll.api.domain.medico;
//dto. Usado para devolver apenas o necessário para a listagem, ou uso do momento
public record DadosListagemMedicos(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicos(Medico medico) { //construtor para inserir as informações no record a partir de um medico
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
