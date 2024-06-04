package med.voll.api.medico;
//Repository
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> { //JpaRepository define a entidade a usar e o tipo da chave primária
}
