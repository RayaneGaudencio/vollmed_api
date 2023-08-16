package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // baseado em nomenclaturas específicas o próprio JPA consegue criar consultas
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(""" 
            select m from Medico m 
            where
            m.ativo = 1 
            and m.especialidade = :especialidade
            and 
            m.id not in(
                select c.medico.id from Consulta cross 
                where 
                c.data = :data
            )
            order by rand()
            limit 1
            """) // os dois pontos antes da especialidade quer dizer que é o parâmetro do método, que são exatamente iguais
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);


    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    Boolean findAtivoById(Long id);
}
