package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);


    @Query(value = """
        select m from medicos m
        where
        m.ativo = true
        and
        m.especialidade = :especialidade
        and
        m.id not in(
            select c.medicos.id from Consulta c
            where
            c.data = :data
        and
            c.motivoCancelamento is null
        )
        order by rand()
        limit 1
    """, nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo FROM medicos m
            WHERE m.id = :id
        """)
    Boolean findAtivoById(Long id);
}
