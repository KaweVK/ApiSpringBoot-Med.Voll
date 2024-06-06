package med.voll.api.medico;
//Repository
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> { //JpaRepository define a entidade a usar e o tipo da chave primária
    Page<Medico> findAllByAtivoTrue(Pageable paginacao); //o nome escrito corretamente facilita para que a consulta seja criada automaticamente
}