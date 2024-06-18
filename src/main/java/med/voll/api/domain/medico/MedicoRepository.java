package med.voll.api.domain.medico;
//Repository
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> { //JpaRepository define a entidade a usar e o tipo da chave primária
    Page<Medico> findAllByAtivoTrue(Pageable paginacao); //o nome escrito corretamente facilita para que a consulta seja criada automaticamente

    @Query("""
            select m from Medico m
            where
            m.ativo = 1
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)//query é usado para implementar consulta no banco de dados
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """) //pega o valor do campo ativo do médico de id passado
    boolean findAtivoById(Long idMedico);
}